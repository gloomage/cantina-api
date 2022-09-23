package com.cantina.controllers;

import com.cantina.dtos.TcktDto;
import com.cantina.models.TcktModel;
import com.cantina.services.TcktService;
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
@RequestMapping("/tickets")
public class TcktController {

    final TcktService tcktService;

    public TcktController(TcktService tcktService){
        this.tcktService = tcktService;
    }

    @GetMapping
    public ResponseEntity<Page<TcktModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(tcktService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTckt(@PathVariable(value = "id") UUID id){
        Optional<TcktModel> tcktModelOptional = tcktService.findById(id);
        if(!tcktModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tckt not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tcktModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveTckt (@RequestBody @Valid TcktDto tcktDto){
        var tcktModel = new TcktModel();
        BeanUtils.copyProperties(tcktDto, tcktModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(tcktService.save(tcktModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTckt(@PathVariable(value = "id") UUID id, @RequestBody @Valid TcktDto tcktDto){
        Optional<TcktModel> tcktModelOptional = tcktService.findById(id);
        if(!tcktModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tckt not found.");
        }
        var tcktModel = new TcktModel();
        BeanUtils.copyProperties(tcktDto, tcktModel);
        tcktModel.setId(tcktModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tcktService.save(tcktModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTckt(@PathVariable(value = "id") UUID id){
        Optional<TcktModel> tcktModelOptional = tcktService.findById(id);
        if(!tcktModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tckt not found.");
        }
        tcktService.delete(tcktModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tckt deleted successfully.");
    }
}
