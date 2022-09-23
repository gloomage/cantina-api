package com.cantina.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_IVEN")
public class IvenModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VendModel vendModel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdModel prodModel;

    @Column(nullable = false)
    private Integer seqiven;

    @Column(nullable = false)
    private Integer qntiven;

    @Column(nullable = false)
    private BigDecimal vlbiven;

    @Column(nullable = false)
    private BigDecimal vlliven;

    @Column(nullable = true)
    private BigDecimal dsriven;

    @Column(nullable = true)
    private BigDecimal dspiven;


}
