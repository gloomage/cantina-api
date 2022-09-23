package com.cantina.controllers;

import com.cantina.dtos.MeioDto;
import com.cantina.models.MeioModel;
import com.cantina.services.MeioService;
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
@RequestMapping("/meios")
public class MeioController {

    final MeioService meioService;

    public MeioController(MeioService meioService){
        this.meioService = meioService;
    }

    @GetMapping
    public ResponseEntity<Page<MeioModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(meioService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMeio(@PathVariable(value = "id") UUID id){
        Optional<MeioModel> meioModelOptional = meioService.findById(id);
        if(!meioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(meioModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveMeio (@RequestBody @Valid MeioDto meioDto){
        var meioModel = new MeioModel();
        BeanUtils.copyProperties(meioDto, meioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(meioService.save(meioModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMeio(@PathVariable(value = "id") UUID id, @RequestBody @Valid MeioDto meioDto){
        Optional<MeioModel> meioModelOptional = meioService.findById(id);
        if(!meioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio not found.");
        }
        var meioModel = new MeioModel();
        BeanUtils.copyProperties(meioDto, meioModel);
        meioModel.setId(meioModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(meioService.save(meioModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeio(@PathVariable(value = "id") UUID id){
        Optional<MeioModel> meioModelOptional = meioService.findById(id);
        if(!meioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio not found.");
        }
        meioService.delete(meioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Meio deleted successfully.");
    }
}
