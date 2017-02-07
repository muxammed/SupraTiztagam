package ru.supratiztagam.supratiztagam.models;

/**
 * Created by muxammed on 26.01.2017.
 */
public class User {

    int uid;
    int wwwid;
    String uname;
    String upass;
    String utoken;
    String unumber;
    int uisactive;
    int ulogedin;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getWwwid() {
        return wwwid;
    }

    public void setWwwid(int wwwid) {
        this.wwwid = wwwid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public String getUnumber() {
        return unumber;
    }

    public void setUnumber(String unumber) {
        this.unumber = unumber;
    }

    public int getUisactive() {
        return uisactive;
    }

    public void setUisactive(int uisactive) {
        this.uisactive = uisactive;
    }

    public int getUlogedin() {
        return ulogedin;
    }

    public void setUlogedin(int ulogedin) {
        this.ulogedin = ulogedin;
    }
}
