package com.cantina.services;

import com.cantina.models.VendModel;
import com.cantina.repositories.VendRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendService {

    final VendRepository vendRepository;

    public VendService(VendRepository vendRepository){
        this.vendRepository = vendRepository;
    }

    public Page<VendModel> findAll(Pageable pageable){
        return vendRepository.findAll(pageable);

    }

    /*  Acões com Cascete
    *   1º save
    *   2º update
    *   3º delete
    * */

    @Transactional
    public VendModel save(VendModel vendModel) {
        return vendRepository.save(vendModel);
    }

    public Optional<VendModel> findById(UUID id) {
        return vendRepository.findById(id);
    }

    @Transactional
    public void delete(VendModel vendModel) {
        vendRepository.delete(vendModel);
    }


}
