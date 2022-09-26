package com.cantina.repositories;

import com.cantina.models.Pai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaiRepository extends JpaRepository<Pai, UUID> {
}
