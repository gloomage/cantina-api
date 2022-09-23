package com.cantina.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MeioDto {

    @NotBlank
    private String nommeio;

}
