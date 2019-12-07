package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class Neighbors {

    /** uid A */
    private int uidA;

    /** uid B */
    private int uidB;

    /** ntime */
    private Date nTime;

}