package com.cantina.repositories;

import com.cantina.models.IvenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IvenRepository extends JpaRepository<IvenModel, UUID> {
}
