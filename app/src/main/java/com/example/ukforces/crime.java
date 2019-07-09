package com.example.ukforces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class crime extends AppCompatActivity {
    CardView loc,forc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        loc=findViewById(R.id.loc);
        forc=findViewById(R.id.forc);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),searchloc.class);
                startActivity(intent);
            }
        });
        forc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),searchforc.class);
                startActivity(intent);
            }
        });
    }
}
