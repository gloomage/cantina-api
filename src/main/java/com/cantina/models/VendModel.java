package com.cantina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_VEND")
public class VendModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "parc_id",
            nullable = false
    )
    private ParcModel parceiro;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "venda"
    )
    private List<IvenModel> itens;

    @Column(nullable = false)
    private LocalDateTime datvend;

    @Column(nullable = false)
    private BigDecimal vlbvend;

    @Column(nullable = false)
    private BigDecimal vllvend;

    @Column(nullable = false)
    private BigDecimal dsrvend;

    @Column
    private BigDecimal dspvend;

    @Column(nullable = false)
    private String sttvend;

}
