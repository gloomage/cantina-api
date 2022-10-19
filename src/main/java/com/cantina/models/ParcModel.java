package com.cantina.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TB_PARC")
public class ParcModel implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 60)
    private String nomparc;

    @Column(nullable = true, length = 50)
    private String emlparc;

    @Column(nullable = true, length = 8)
    private String cepparc; //Mudou String from String

    @Column(nullable = true, length = 4)
    private String casparc;

    @Column(nullable = true, length = 11)
    private String telparc;

    /* Trocar por um enum de status de cliente*/
    @Column(nullable = true, length = 50)
    private String sttparc;

    @Column(nullable = true, length = 11)
    private String cpfparc;

    @Column(nullable = true, length = 14)
    private String cnpparc;

    @Column(nullable = true)
    private BigDecimal vlcparc;

    @Column(nullable = true)
    private BigDecimal vldparc;
}
