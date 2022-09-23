package com.cantina.services;

import com.cantina.models.TcktModel;
import com.cantina.repositories.TcktRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class TcktService {

    final TcktRepository tcktRepository;

    public TcktService(TcktRepository tcktRepository){
        this.tcktRepository = tcktRepository;
    }

    public Page<TcktModel> findAll(Pageable pageable) {
        return tcktRepository.findAll(pageable);
    }

    @Transactional
    public TcktModel save(TcktModel tcktModel) {
        return tcktRepository.save(tcktModel);
    }

    public Optional<TcktModel> findById(UUID id) {
        return tcktRepository.findById(id);
    }

    @Transactional
    public void delete(TcktModel tcktModel) {
        tcktRepository.delete(tcktModel);
    }
}
