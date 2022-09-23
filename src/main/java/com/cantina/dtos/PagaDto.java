package com.cantina.dtos;

import com.cantina.enums.SttPaga;
import com.cantina.models.MeioModel;
import lombok.Data;

@Data
public class PagaDto {

    private MeioModel meioModel;

    private SttPaga sttPaga;

}
