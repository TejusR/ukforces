package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class OutcomeStatus {
    @SerializedName("category")
    String cat;
    @SerializedName("date")
    String date;
    public OutcomeStatus(String cat, String date) {
        this.cat = cat;
        this.date = date;
    }
    public String getCat() {
        return cat;
    }

    public String getDate() {
        return date;
    }
}
