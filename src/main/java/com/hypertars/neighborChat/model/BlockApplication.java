package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class BlockApplication {

    /** app id */
    private int applicant;

    private int bid;

    /** batime */
    private Date baTime;

    /** bid */
    private int bid;

    /** txt */
    private String txt;

    /** accepts */
    private int accepts;

    /** decisions */
    private int decisions;

    private boolean status;

}