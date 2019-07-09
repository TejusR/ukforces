package com.example.ukforces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

public class force_activity extends AppCompatActivity {
    RecyclerView r;
    Forces_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_activity);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi api=retrofit.create(MyApi.class);
        Call<List<Forces>> call= api.getForces();
        call.enqueue(new Callback<List<Forces>>() {
            @Override
            public void onResponse(Call<List<Forces>> call, Response<List<Forces>> response) {
                writeRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<Forces>> call, Throwable t) {
                Toast.makeText(force_activity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void writeRecycler(List<Forces> response){
        r=findViewById(R.id.forcerecyclerview);
        adapter=new Forces_Adapter(response,force_activity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(force_activity.this);
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
