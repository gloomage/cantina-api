package com.cantina.repositories;

import com.cantina.models.ParcModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParcRepository extends JpaRepository<ParcModel, UUID> {
}
