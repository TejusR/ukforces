package com.example.ukforces;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("latitude")
    String lat;
    @SerializedName("longitude")
    String lon;
    @SerializedName("street")
    Street street;

    public Location(String lat, String lon, Street street) {
        this.lat = lat;
        this.lon = lon;
        this.street = street;
    }
    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public Street getStreet() {
        return street;
    }
}
