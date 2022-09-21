package com.cantina.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProdDto {

    @NotBlank
    private String nomprod;

    @NotBlank
    private String medprod;

    private String desprod;

    @NotNull
    private BigDecimal vlrprod;

    @NotNull
    private Integer qntprod;

}
