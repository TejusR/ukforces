package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class SpecificForceclass {
    @SerializedName("description")
    private String des;
    @SerializedName("url")
    private String url;
    @SerializedName("telephone")
    private String phone;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public SpecificForceclass(String des, String url, String phone, String id, String name) {
        this.des = des;
        this.url = url;
        this.phone = phone;
        this.id = id;
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
