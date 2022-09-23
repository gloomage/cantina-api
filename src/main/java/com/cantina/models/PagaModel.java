package com.cantina.models;

import com.cantina.enums.SttPaga;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PAGA")
public class PagaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    private VendModel vendModel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MeioModel meioModel;

    @Column(nullable = false)
    private SttPaga sttPaga;

}
