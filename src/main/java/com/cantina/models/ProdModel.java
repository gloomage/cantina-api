package com.cantina.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PROD")
public class ProdModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String nomprod;

    @Column(nullable = false, length = 20)
    private String medprod;

    @Column(nullable = true, length = 300)
    private String desprod;

    @Column(nullable = false)
    private BigDecimal vlrprod;

    @Column(nullable = false)
    private Integer qntprod;
}
