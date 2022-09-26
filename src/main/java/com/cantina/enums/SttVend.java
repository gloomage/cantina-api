package com.cantina.enums;

public enum SttVend {

    PAG("PAGA", 1),
    PEN("PENDENTE", 2),
    CAN("CANCELADA", 3);

    private final String nomStts;
    private final Integer seqStts;


    SttVend(String nomStts, Integer seqStts) {
        this.nomStts = nomStts;
        this.seqStts = seqStts;
    }
}
