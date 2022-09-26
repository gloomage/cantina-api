package com.cantina.controllers;


import com.cantina.dtos.VendDto;
import com.cantina.models.VendModel;
import com.cantina.services.VendService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/vendas")
public class VendController {

    final VendService vendService;

    public VendController(VendService vendService){
        this.vendService = vendService;
    }

    @GetMapping
    public ResponseEntity<Page<VendModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vendService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> saveVend (@RequestBody @Valid VendDto vendDto){
        var vendModel = new VendModel();
        BeanUtils.copyProperties(vendDto, vendModel);

        /* Inserindo valores automaticos */
        vendModel.setDatvend(LocalDateTime.now());
//        vendModel.setVlbvend(new BigDecimal());
//        vendModel.setVllvend();

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
