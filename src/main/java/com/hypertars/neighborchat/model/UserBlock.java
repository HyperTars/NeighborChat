package com.hypertars.neighborchat.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserBlock {

    /** uid */
    private int uid;

    /** bid */
    private int bid;

    /** ubtime */
    private Date ubTime;

}