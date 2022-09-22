package com.cantina.repositories;

import com.cantina.models.ProdModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/* Anotação
 * @Repository -> Demarca a class como um repositorio
 *  */

@Repository
public interface ProdRepository extends JpaRepository<ProdModel, UUID> {
}
