package com.cantina.services;

import com.cantina.models.ProdModel;
import com.cantina.repositories.ProdRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdService {

    final ProdRepository prodRepository;

    public ProdService(ProdRepository prodRepository){
        this.prodRepository = prodRepository;
    }

    public List<ProdModel> findAll() {
        return prodRepository.findAll();
    }

    @Transactional
    public ProdModel save(ProdModel prodModel) {
        return prodRepository.save(prodModel);
    }

    public Optional<ProdModel> findById(UUID id) {
        return prodRepository.findById(id);
    }

    @Transactional
    public void delete(ProdModel prodModel) {
        prodRepository.delete(prodModel);
    }

}
