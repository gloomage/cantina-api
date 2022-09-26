package com.cantina.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Pai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "pai"
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Filho> list;

//    @OneToMany(
//            mappedBy = "pai",
//            cascade = CascadeType.ALL
//    )
//    private List<Filho> filhos;
}
