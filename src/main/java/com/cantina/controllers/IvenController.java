package com.cantina.controllers;

import com.cantina.dtos.IvenDto;
import com.cantina.models.IvenModel;
import com.cantina.services.IvenService;
import com.cantina.services.IvenService;
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
@RequestMapping("/itens-vendas")
public class IvenController {

    final IvenService ivenService;

    public IvenController(IvenService ivenService){
        this.ivenService = ivenService;
    }

    @GetMapping
    public ResponseEntity<Page<IvenModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(ivenService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getIven(@PathVariable(value = "id") UUID id){
        Optional<IvenModel> ivenModelOptional = ivenService.findById(id);
        if(!ivenModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Iven not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ivenModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveIven (@RequestBody @Valid IvenDto ivenDto){
        var ivenModel = new IvenModel();
        BeanUtils.copyProperties(ivenDto, ivenModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ivenService.save(ivenModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateIven(@PathVariable(value = "id") UUID id, @RequestBody @Valid IvenDto IvenDto){
        Optional<IvenModel> ivenModelOptional = ivenService.findById(id);
        if(!ivenModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Iven not found.");
        }
        var IvenModel = new IvenModel();
        BeanUtils.copyProperties(IvenDto, IvenModel);
        IvenModel.setId(ivenModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(ivenService.save(IvenModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIven(@PathVariable(value = "id") UUID id){
        Optional<IvenModel> ivenModelOptional = ivenService.findById(id);
        if(!ivenModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Iven not found.");
        }
        ivenService.delete(ivenModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Iven deleted successfully.");
    }
}
