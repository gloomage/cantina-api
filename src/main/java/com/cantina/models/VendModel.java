package com.cantina.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_VEND")
public class VendModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn
    private ParcModel parcModel;

    @Column(nullable = false)
    private LocalDateTime datvend;

    @Column(nullable = false)
    private BigDecimal vlbvend;

    @Column(nullable = false)
    private BigDecimal vllvend;

    @Column(nullable = true)
    private BigDecimal dsrvend;

    @Column(nullable = true)
    private BigDecimal dspvend;

    @Column(nullable = true)
    private String sttvend;



}
