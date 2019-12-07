package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class Friends {

    /** uid A */
    private int uidA;

    /** uid B */
    private int uidB;

    /** ftime */
    private Date fTime;

}