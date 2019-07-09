package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class Street {
    @SerializedName("name")
    String name;
    @SerializedName("id")
    String id;

    public Street(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
