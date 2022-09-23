package com.cantina.controllers;

import com.cantina.dtos.PagaDto;
import com.cantina.models.PagaModel;
import com.cantina.services.PagaService;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/pagamentos")
public class PagaController {

    final PagaService pagaService;

    public PagaController(PagaService pagaService){
        this.pagaService = pagaService;
    }

    @GetMapping
    public ResponseEntity<Page<PagaModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pagaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaga(@PathVariable(value = "id") UUID id){
        Optional<PagaModel> pagaModelOptional = pagaService.findById(id);
        if(!pagaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paga not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pagaModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> savePaga (@RequestBody @Valid PagaDto pagaDto){
        var pagaModel = new PagaModel();
        BeanUtils.copyProperties(pagaDto, pagaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagaService.save(pagaModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePaga(@PathVariable(value = "id") UUID id, @RequestBody @Valid PagaDto pagaDto){
        Optional<PagaModel> pagaModelOptional = pagaService.findById(id);
        if(!pagaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paga not found.");
        }
        var pagaModel = new PagaModel();
        BeanUtils.copyProperties(pagaDto, pagaModel);
        pagaModel.setId(pagaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pagaService.save(pagaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaga(@PathVariable(value = "id") UUID id){
        Optional<PagaModel> pagaModelOptional = pagaService.findById(id);
        if(!pagaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paga not found.");
        }
        pagaService.delete(pagaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Paga deleted successfully.");
    }
}
