package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class FriendApplication {

    /** app id */
    private int applicant;

    /** recip id */
    private int recipient;

    /** txt */
    private String txt;

    /** fatime */
    private Date faTime;

    /** decision */
    private Boolean decision;

}