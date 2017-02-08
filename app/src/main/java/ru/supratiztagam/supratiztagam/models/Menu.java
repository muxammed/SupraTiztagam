package ru.supratiztagam.supratiztagam.models;

/**
 * Created by muhammed on 08.02.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("mid")
    @Expose
    public String mid;
    @SerializedName("mgrpid")
    @Expose
    public String mgrpid;
    @SerializedName("mname")
    @Expose
    public String mname;
    @SerializedName("mdesc")
    @Expose
    public String mdesc;
    @SerializedName("mmsr")
    @Expose
    public String mmsr;
    @SerializedName("mprice")
    @Expose
    public String mprice;

    @Override
    public String toString() {
        return "Menu{" +
                "mid='" + mid + '\'' +
                ", mgrpid='" + mgrpid + '\'' +
                ", mname='" + mname + '\'' +
                ", mdesc='" + mdesc + '\'' +
                ", mmsr='" + mmsr + '\'' +
                ", mprice='" + mprice + '\'' +
                '}';
    }
}