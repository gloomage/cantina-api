package com.cantina.services;

import com.cantina.models.ParcModel;
import com.cantina.repositories.ParcRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParcService {

    final ParcRepository parcRepository;

    public ParcService(ParcRepository parcRepository){
        this.parcRepository = parcRepository;
    }

    public Page<ParcModel> findAll(Pageable pageable) {
        return parcRepository.findAll(pageable);
    }

    public List<ParcModel> findAllbyList() {
        return parcRepository.findAll();
    }

    @Transactional
    public ParcModel save(ParcModel parcModel) {
        return parcRepository.save(parcModel);
    }

    public Optional<ParcModel> findById(UUID id) {
        return parcRepository.findById(id);
    }

    @Transactional
    public void delete(ParcModel parcModel) {
        parcRepository.delete(parcModel);
    }

}
