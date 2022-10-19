package com.cantina.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ParcDto {

    @NotBlank
    private String nomparc;

    private String emlparc;

    private String cepparc;

    private String casparc;

    private String telparc;

    private String sttparc;

    private String cpfparc;

    private String cnpparc;

    private BigDecimal vlcparc;

    private BigDecimal vldparc;
}
