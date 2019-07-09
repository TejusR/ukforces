package com.example.ukforces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class disloccrime extends AppCompatActivity {
 String lat,lon,mon;
 TextView latitude,longitude,streetid,streetname,id,cat,date;
 CardView m;
 int map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disloccrime);
        lat=getIntent().getStringExtra("lat");
        lon=getIntent().getStringExtra("lon");
        mon=getIntent().getStringExtra("date");
        map=getIntent().getIntExtra("location",0);
        cat=findViewById(R.id.cat);
        date=findViewById(R.id.date);
        latitude=findViewById(R.id.latitude);
        longitude=findViewById(R.id.longitude);
        streetid=findViewById(R.id.streetid);
        streetname=findViewById(R.id.streetname);
        id=findViewById(R.id.crimeid);
        latitude.setText(lat);
        longitude.setText(lon);
        streetid.setText(getIntent().getStringExtra("streetid"));
        streetname.setText(getIntent().getStringExtra("streetname"));
        id.setText(getIntent().getStringExtra("id"));
        date.setText(mon);
        cat.setText(getIntent().getStringExtra("cat"));
        m=findViewById(R.id.showmap);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map==1) {
                    Intent intent = new Intent(getApplicationContext(), mapactivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Location Not Available",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
