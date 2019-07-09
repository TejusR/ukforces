package com.example.ukforces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface MyApi {
    String BASE_URL="https://data.police.uk/api/";

    @GET("forces")
    Call<List<Forces>> getForces();

    @GET("forces/{Id}")
    Call<SpecificForceclass> getspecific(@Path("Id") String Id);
    @GET
    Call<List<Crimeloc>> getCrimeLoc(@Url String url);
}
