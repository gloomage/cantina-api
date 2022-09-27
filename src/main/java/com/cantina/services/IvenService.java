package com.cantina.services;

import com.cantina.models.IvenModel;
import com.cantina.models.ParcModel;
import com.cantina.repositories.IvenRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class IvenService{

    final IvenRepository ivenRepository;

    public IvenService(IvenRepository ivenRepository){
        this.ivenRepository = ivenRepository;
    }

    public Page<IvenModel> findAll(Pageable pageable) {
        return ivenRepository.findAll(pageable);
    }

    @Transactional
    public IvenModel save(IvenModel ivenModel) {
        return ivenRepository.save(ivenModel);
    }

    public Optional<IvenModel> findById(UUID id) {
        return ivenRepository.findById(id);
    }

    @Transactional
    public void delete(IvenModel ivenModel) {
        ivenRepository.delete(ivenModel);
    }
}
