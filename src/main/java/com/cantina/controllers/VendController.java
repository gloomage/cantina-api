package com.cantina.controllers;


import com.cantina.dtos.VendDto;
import com.cantina.models.*;
import com.cantina.services.ParcService;
import com.cantina.services.ProdService;
import com.cantina.services.VendService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/vendas")
public class VendController {

    final VendService vendService;

    final ProdService prodService;

    final ParcService parcService;

    public VendController(VendService vendService, ProdService prodService, ParcService parcService){
        this.vendService = vendService;
        this.prodService = prodService;
        this.parcService = parcService;
    }

    @GetMapping
    public ResponseEntity<Page<VendModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vendService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> saveVend (@RequestBody @Valid VendDto vendDto){

        //DTO
        Optional<ParcModel> parcModelOptional = parcService.findById(UUID.fromString("b3017444-f780-4a5e-93fd-1418bc31eedf")); //cliente
        Optional<ProdModel> prodModelOptional1 = prodService.findById(UUID.fromString("055b1694-7bae-477e-bae5-a70d315b9ff3")); //Coockie - 5,00
        Optional<ProdModel> prodModelOptional2 = prodService.findById(UUID.fromString("0cb4eff1-9600-4ef8-be0d-63f04cd3028a")); //Cerveja - 6,00

        if(!parcModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parc not found");
        }
        vendDto.setParcModel(parcModelOptional.get());


        var vendModel = new VendModel();
        BeanUtils.copyProperties(vendDto, vendModel);

        /* JA CONTEM O PARCEIRO / LIST<ITENS> / DESCONTOS R&P */

        /* Data */
        vendModel.setDatvend(LocalDateTime.now());
        /* Valor Bruto */
        BigDecimal vlrTotalVend = new BigDecimal("0");
        for (IvenModel ivenModel : vendModel.getItens()){
            vlrTotalVend.add(ivenModel.getVlbiven());
        }
        vendModel.setVlbvend(vlrTotalVend);
        /* Valor Liquido */
        vendModel.setVllvend(new BigDecimal("30.00"));

        vendModel.setSttvend("Em aberto");

        //INSERIR VENDA NOS ITENS DO PRODUTO

        for(IvenModel ivenModel : vendModel.getItens()){
            ivenModel.setVenda(vendModel);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vendService.save(vendModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVend(@PathVariable(value = "id") UUID id, @RequestBody @Valid VendDto vendDto){
        Optional<VendModel> vendModelOptional = vendService.findById(id);
        if(!vendModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("prod not found.");
        }
        var vendModel = new VendModel();
        BeanUtils.copyProperties(vendDto, vendModel);


        vendModel.setId(vendModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(vendService.save(vendModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProd(@PathVariable(value = "id") UUID id){
        Optional<VendModel> vendModelOptional = vendService.findById(id);
        if(!vendModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vend not found.");
        }
        vendService.delete(vendModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vend deleted successfully.");
    }

}
