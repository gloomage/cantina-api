package com.cantina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /* VENDA */

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vend_id")
    private VendModel venda;

    /* PRODUTO */

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private ProdModel produto;

    @Column
    private Integer seqiven;

    @Column
    private Integer qntiven;

    @Column
    private BigDecimal vlbiven;

    @Column
    private BigDecimal vlliven;

    @Column
    private BigDecimal dsriven;

    @Column
    private BigDecimal dspiven;


}
