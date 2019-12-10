package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class FriendApplication {

    /** app id */
    private int applicant;

    /** recip id */
    private int recipient;

    /** fatime */
    private Date faTime;

    /** txt */
    private String txt;
}