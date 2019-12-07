package com.hypertars.neighborchat.model;

import lombok.Data;

import java.util.Date;

@Data
public class BlockApplication {

    /** app id */
    private int applicant;

    /** batime */
    private Date baTime;

    /** txt */
    private String txt;

    /** accepts */
    private int accepts;

    /** decisions */
    private int decisions;

}