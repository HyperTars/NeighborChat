package com.hypertars.neighborChat.model;
import lombok.Data;

import java.util.Objects;

@Data
public class Users {

    /** private attributes */
    private int uid;

    private String uname;

    private String passwd;

    private String email;

    private String fName;

    private String lName;

    private String addr1;

    private String addr2;

    private String intro;

    private String photo;

    private String nRange;

    private String lastLog;

    private short notify;

    /** public get */
    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getIntro() {
        return intro;
    }

    public String getPhoto() {
        return photo;
    }

    public String getnRange() {
        return nRange;
    }

    public String getLastLog() {
        return lastLog;
    }

    public short getNotify() {
        return notify;
    }

    /** public set */
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setnRange(String nRange) {
        this.nRange = nRange;
    }

    public void setLastLog(String lastLog) {
        this.lastLog = lastLog;
    }

    public void setNotify(short notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", intro='" + intro + '\'' +
                ", photo='" + photo + '\'' +
                ", nRange='" + nRange + '\'' +
                ", lastLog='" + lastLog + '\'' +
                ", notify=" + notify +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getUid() == users.getUid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getUname(), getPasswd(), getEmail(), getfName(), getlName(), getAddr1(), getAddr2(), getIntro(), getPhoto(), getnRange(), getLastLog(), getNotify());
    }
}
