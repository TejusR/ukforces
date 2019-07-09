package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class Crimeloc {
    @SerializedName("id")
    String id;
    @SerializedName("category")
    String cat;
    @SerializedName("persistent_id")
    String pi;
    @SerializedName("outcome_status")
    OutcomeStatus out;
    @SerializedName("location")
    Location loc;
    @SerializedName("month")
    String mon;
    public boolean isfav;
    public Crimeloc(String id, String cat, String pi, OutcomeStatus out, Location loc,String mon) {
        this.id = id;
        this.cat = cat;
        this.pi = pi;
        this.out = out;
        this.loc = loc;
        this.mon=mon;
        this.isfav=false;
    }
    public String getMon(){
        return mon;
    }
    public String getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }

    public String getPi() {
        return pi;
    }

    public OutcomeStatus getOut() {
        return out;
    }

    public Location getLoc() {
        return loc;
    }

    public boolean isIsfav() {
        return isfav;
    }
}
