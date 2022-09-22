package com.cantina.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ParcDto {

    @NotBlank
    private String nomparc;

    private String emlparc;

    private String cepparc;

    private Integer casparc;

    private String telparc;

    private String sttparc;

    private String cpfparc;

    private String cnpparc;
}
