package com.cantina.controllers;

import com.cantina.dtos.ParcDto;
import com.cantina.models.ParcModel;
import com.cantina.services.ParcService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/parceiros")
public class ParcController {

    final ParcService parcService;

    public ParcController(ParcService parcService){
        this.parcService = parcService;
    }

    @GetMapping
    public ResponseEntity<List<ParcModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(parcService.findAllbyList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParc(@PathVariable(value = "id") UUID id){
        Optional<ParcModel> parcModelOptional = parcService.findById(id);
        if(!parcModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parc not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parcModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveParc (@RequestBody @Valid ParcDto parcDto){
        var parcModel = new ParcModel();
        BeanUtils.copyProperties(parcDto, parcModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(parcService.save(parcModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParc(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParcDto parcDto){
        Optional<ParcModel> parcModelOptional = parcService.findById(id);
        if(!parcModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parc not found.");
        }
        var parcModel = new ParcModel();
        BeanUtils.copyProperties(parcDto, parcModel);
        parcModel.setId(parcModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(parcService.save(parcModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParc(@PathVariable(value = "id") UUID id){
        Optional<ParcModel> parcModelOptional = parcService.findById(id);
        if(!parcModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parc not found.");
        }
        parcService.delete(parcModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parc deleted successfully.");
    }
}
