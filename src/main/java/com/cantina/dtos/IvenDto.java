package com.cantina.dtos;

import com.cantina.models.ProdModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IvenDto {

    private ProdModel prodModel;

    private Integer qntiven;

    private BigDecimal dsriven;

    private BigDecimal dspiven;

}
