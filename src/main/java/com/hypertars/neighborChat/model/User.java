package com.hypertars.neighborChat.model;
import lombok.Data;

@Data
public class User {
    /** user id */
    private int uid;

    /** user name */
    private String uname;

    /** user pass */
    private String passwd;

    /** first name */
    private String fName;

    /** last name */
    private String lName;

    /** addr 1 */
    private String addr1;

    /** addr 2 */
    private String addr2;

    /** intro */
    private String intro;

    /** photo */
    private String photo;

    /** nrange */
    private String nRange;

    /** lastLog */
    private String lastLog;
}
