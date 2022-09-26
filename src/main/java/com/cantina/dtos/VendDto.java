package com.cantina.dtos;

import com.cantina.models.IvenModel;
import com.cantina.models.ParcModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VendDto {

    private ParcModel parcModel;

    private List<IvenModel> listIven;

    private BigDecimal dsrvend;

    private BigDecimal dspvend;

    private String sttvend;
}
