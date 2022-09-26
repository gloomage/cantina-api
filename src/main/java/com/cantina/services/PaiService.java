package com.cantina.services;

import com.cantina.models.PagaModel;
import com.cantina.models.Pai;
import com.cantina.repositories.PaiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaiService {

    final PaiRepository paiRepository;

    public PaiService(PaiRepository paiRepository){
        this.paiRepository = paiRepository;
    }

    public Page<Pai> findAll(Pageable pageable) {
        return paiRepository.findAll(pageable);
    }

    @Transactional
    public Pai save(Pai pai) {
        return paiRepository.save(pai);
    }
}
