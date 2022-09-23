package com.cantina.repositories;

import com.cantina.models.MeioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeioRepository extends JpaRepository<MeioModel, UUID> {
}
