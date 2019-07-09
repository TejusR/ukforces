package com.example.ukforces;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

public class specificforce extends AppCompatActivity {
  String Id;
  SpecificForceclass sf;
  TextView tvname,tvdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificforce);
        Id=getIntent().getExtras().getString("id");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi api=retrofit.create(MyApi.class);
        Call<SpecificForceclass> call=api.getspecific(Id);
        call.enqueue(new Callback<SpecificForceclass>() {
            @Override
            public void onResponse(Call<SpecificForceclass> call, Response<SpecificForceclass> response) {
                sf=response.body();
                tvname=findViewById(R.id.textView);
                tvdes=findViewById(R.id.textView4);
                tvname.setText(sf.getName());
                tvdes.setText(sf.getDes());
            }

            @Override
            public void onFailure(Call<SpecificForceclass> call, Throwable t) {

            }
        });
    }
}
