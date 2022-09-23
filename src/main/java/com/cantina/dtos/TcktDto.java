package com.cantina.dtos;

import com.cantina.models.VendModel;
import lombok.Data;

@Data
public class TcktDto {

    private VendModel vendModel;

    private Integer seqtckt;

}
