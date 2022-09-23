package com.cantina.repositories;

import com.cantina.models.TcktModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TcktRepository extends JpaRepository<TcktModel, UUID> {
}
