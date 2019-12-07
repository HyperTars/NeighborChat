package com.hypertars.neighborchat.model;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {

    /** msgid */
    private int msgid;

    /** uid */
    private int uid;

    /** rTime */
    private Date rTime;

    private String txt;

    private String coord;

}