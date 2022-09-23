package com.cantina.repositories;

import com.cantina.models.PagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PagaRepository extends JpaRepository<PagaModel, UUID> {
}
