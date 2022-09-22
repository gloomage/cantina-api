package com.cantina.models;

import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/* Anotações
 * Entity -> Identificando como Entidade
 * Table -> Identificando como um Table no Data Base
 * Data -> Gettes e Settes pelo Lombok
 */

@Data
@Entity
@Table(name = "TB_PROD")
public class ProdModel implements Serializable {

    /* Primary Key UUID */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /* Propriedade
    * Column -> Marcação de coluna
    * */

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
