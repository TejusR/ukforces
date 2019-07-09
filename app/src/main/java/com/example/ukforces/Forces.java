package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class Forces {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public Forces(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
