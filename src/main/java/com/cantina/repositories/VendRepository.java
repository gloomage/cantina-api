package com.cantina.repositories;

import com.cantina.models.VendModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendRepository extends JpaRepository<VendModel, UUID> {
}
