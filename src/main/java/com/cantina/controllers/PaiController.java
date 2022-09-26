package com.cantina.controllers;

import com.cantina.dtos.MeioDto;
import com.cantina.models.Filho;
import com.cantina.models.MeioModel;
import com.cantina.models.Pai;
import com.cantina.services.MeioService;
import com.cantina.services.PaiService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.TreeSelectionListener;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3680)
@RequestMapping("/pais")
public class PaiController {

    final PaiService paiService;

    public PaiController(PaiService paiService){
        this.paiService = paiService;
    }

    @GetMapping
    public ResponseEntity<Page<Pai>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(paiService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> savePai (@RequestBody @Valid Pai pai){
        List<Filho> filhos = new ArrayList<>();

        var filho1 = new Filho();
        var filho2 = new Filho();
        filho1.setNome("Matheus");
        filho2.setNome("Gabriel");

        pai.setList(filhos);

        filho1.setPai(pai);
        filho2.setPai(pai);

        filhos.add(filho1);
        filhos.add(filho2);

        System.out.println("-------------");
        System.out.println("Antes de Salvar : " + pai);
        System.out.println("Antes de Salvar : " + pai.getList());

        paiService.save(pai);

        System.out.println("-------------");
        System.out.println("Depois de Salvar : " + pai);
        System.out.println("Antes de Salvar : " + pai.getList());




        System.out.println(ResponseEntity.status(HttpStatus.CREATED).body(pai.getList()));
        System.out.println(ResponseEntity.status(HttpStatus.CREATED).body("-----BODY-----"));

        return ResponseEntity.status(HttpStatus.CREATED).body(pai);
    }
}
