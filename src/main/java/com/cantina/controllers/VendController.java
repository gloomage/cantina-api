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

//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getTckt(@PathVariable(value = "id") UUID id){
//        Optional<TcktModel> tcktModelOptional = tcktService.findById(id);
//        if(!tcktModelOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tckt not found");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(tcktModelOptional.get());
//    }

    @PostMapping
    public ResponseEntity<Object> saveVend (@RequestBody @Valid VendDto vendDto){
        var vendModel = new VendModel();
        BeanUtils.copyProperties(vendDto, vendModel);

        /* Teste de Venda*/
        //Primeiro cria lista de objetos q vem ser inseridos
        List<IvenModel> itens = new ArrayList<>();
        Optional<ParcModel> parcModelOptional = parcService.findById(UUID.fromString("b3017444-f780-4a5e-93fd-1418bc31eedf")); //cliente
        Optional<ProdModel> prodModelOptional1 = prodService.findById(UUID.fromString("055b1694-7bae-477e-bae5-a70d315b9ff3")); //Coockie - 5,00
        Optional<ProdModel> prodModelOptional2 = prodService.findById(UUID.fromString("0cb4eff1-9600-4ef8-be0d-63f04cd3028a")); //Cerveja - 6,00

        var item1 = new IvenModel();
        var item2 = new IvenModel();

        //set prod no item
        item1.setProduto(prodModelOptional1.get());
        item2.setProduto(prodModelOptional2.get());

        //Sequencia no carinho
        item1.setSeqiven(1);
        item1.setSeqiven(2);

        //Quantidade
        item1.setQntiven(2);
        item2.setQntiven(4);

        //Valor bruto
        item1.setVlbiven(new BigDecimal("10.00"));
        item2.setVlbiven(new BigDecimal("24.00"));

        //Valor liquido
        item1.setVlliven(new BigDecimal("10.00"));
        item2.setVlliven(new BigDecimal("20.00"));

        //desconto em reais
        item1.setDsriven(new BigDecimal("0.00"));
        item2.setDsriven(new BigDecimal("4.00"));

        /* ----------------------------------------- */

        //Completa a venda
        vendModel.setParceiro(parcModelOptional.get());
        vendModel.setItens(itens);
        vendModel.setDatvend(LocalDateTime.now());
        vendModel.setVlbvend(new BigDecimal("34.00"));
        vendModel.setVllvend(new BigDecimal("30.00"));
        vendModel.setDsrvend(new BigDecimal("4.00"));
        vendModel.setSttvend("Em aberto");

        //Insere na venda as listas
        itens.add(item1);
        itens.add(item2);

        //salva
        System.out.println(vendModel);
        vendService.save(vendModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cavalooooo");
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
