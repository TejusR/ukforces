package com.example.ukforces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class showFav extends AppCompatActivity {
    DatabaseHelper dh;
    RecyclerView r;
    Crimeloc crime;
    Location loc;
    OutcomeStatus out;
    favAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fav);
        r=findViewById(R.id.favrecycler);
        dh=new DatabaseHelper(this);
        Cursor data=dh.getData();
        ArrayList<Crimeloc> listcrime=new ArrayList<>();
        while(data.moveToNext()){
            if(data.getString(2)==""&&data.getString(3)==""&&data.getString(4)==""&&data.getString(5)=="")
            {
                loc=null;
            }
            else {
                String lat=data.getString(2);
                String lon=data.getString(3);
                String si=data.getString(4);
                String sn=data.getString(5);
                Street str=new Street(si,sn);
                loc=new Location(lat,lon,str);
            }
            if(data.getString(6)=="")
                out=null;
            else{
                String cat=data.getString(6);
                String dat=data.getString(7);
                out=new OutcomeStatus(cat,dat);
             }
             crime=new Crimeloc(data.getString(0),data.getString(1),"",out,loc,data.getString(7));
             listcrime.add(crime);
        }
        adapter=new favAdapter(listcrime,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showFav.this);
        r.setLayoutManager(layoutManager);
        r.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.forcesearch,menu);
        MenuItem searchitem=menu.findItem(R.id.app_bar_search);
        SearchView sv=(SearchView) searchitem.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
