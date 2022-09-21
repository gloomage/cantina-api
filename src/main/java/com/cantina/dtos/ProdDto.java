package com.cantina.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProdDto {

    @NotBlank
    private String nomprod;

    @NotBlank
    private String medprod;

    private String desprod;

    @NotBlank
    private BigDecimal vlrprod;

    @NotBlank
    private Integer qntprod;

}
