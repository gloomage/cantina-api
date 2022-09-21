package com.cantina.controllers;

import com.cantina.dtos.ProdDto;
import com.cantina.models.ProdModel;
import com.cantina.services.ProdService;
import org.hibernate.sql.OracleJoinFragment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/* Anotações
* @RestController ->
* @CrossOrigin ->
* @RequestMapping ->
* */

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/produtos")
public class ProdController {

    final ProdService prodService;

    public ProdController(ProdService prodService){
        this.prodService = prodService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(prodService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> saveProd (@RequestBody @Valid ProdDto prodDto){
        var prodModel = new ProdModel();
        BeanUtils.copyProperties(prodDto, prodModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodService.save(prodModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProd(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdDto prodDto){
        Optional<ProdModel> prodModelOptional = prodService.findById(id);
        if(!prodModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("prod not found.");
        }
        var prodModel = new ProdModel();
        BeanUtils.copyProperties(prodDto, prodModel);
        prodModel.setId(prodModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(prodService.save(prodModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProd(@PathVariable(value = "id") UUID id){
        Optional<ProdModel> prodModelOptional = prodService.findById(id);
        if(!prodModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prod not found.");
        }
        prodService.delete(prodModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Prod deleted successfully.");
    }
}
