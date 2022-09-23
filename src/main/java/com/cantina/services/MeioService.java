package com.cantina.services;

import com.cantina.models.MeioModel;
import com.cantina.repositories.MeioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class MeioService {

    final MeioRepository meioRepository;

    public MeioService(MeioRepository meioRepository){
        this.meioRepository = meioRepository;
    }

    public Page<MeioModel> findAll(Pageable pageable) {
        return meioRepository.findAll(pageable);
    }

    @Transactional
    public MeioModel save(MeioModel meioModel) {
        return meioRepository.save(meioModel);
    }

    public Optional<MeioModel> findById(UUID id) {
        return meioRepository.findById(id);
    }

    @Transactional
    public void delete(MeioModel meioModel) {
        meioRepository.delete(meioModel);
    }
}
