package com.cantina.dtos;

import com.cantina.models.IvenModel;
import com.cantina.models.ParcModel;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendDto {

    /* Escolhe o parceiro*/
    private ParcModel parcModel;

    /* Escolhe os itens da venda */
    private List<IvenModel> itens;

    /* Escolhe o desconto REAIS */
    private BigDecimal dsrvend;

    /* Escolhe o deconto em PORCENTAGEM */

    /* Default Em aberto */
//    private String sttvend;
}
