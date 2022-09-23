package com.cantina.services;

import com.cantina.models.PagaModel;
import com.cantina.repositories.PagaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagaService {

    final PagaRepository pagaRepository;

    public PagaService(PagaRepository pagaRepository){
        this.pagaRepository = pagaRepository;
    }

    public Page<PagaModel> findAll(Pageable pageable) {
        return pagaRepository.findAll(pageable);
    }

    @Transactional
    public PagaModel save(PagaModel pagaModel) {
        return pagaRepository.save(pagaModel);
    }

    public Optional<PagaModel> findById(UUID id) {
        return pagaRepository.findById(id);
    }

    @Transactional
    public void delete(PagaModel pagaModel) {
        pagaRepository.delete(pagaModel);
    }
}
