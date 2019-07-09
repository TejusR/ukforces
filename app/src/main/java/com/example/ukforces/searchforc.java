package com.example.ukforces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;

public class searchforc extends AppCompatActivity {
    EditText force,mon;
    ImageButton search;
    RecyclerView r;
    List<Crimeloc> crime;
    searchlocadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchforc);
        force=findViewById(R.id.forcename);
        mon=findViewById(R.id.forcedate);
        search=findViewById(R.id.imageButton2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(MyApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MyApi api=retrofit.create(MyApi.class);
                Call<List<Crimeloc>> call=api.getCrimeLoc("crimes-no-location?category=all-crime&force="+force.getText().toString()+"&date="+mon.getText().toString());
                call.enqueue(new Callback<List<Crimeloc>>() {
                    @Override
                    public void onResponse(Call<List<Crimeloc>> call, Response<List<Crimeloc>> response) {
                        crime=response.body();
                     writeRecycler(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Crimeloc>> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void writeRecycler(List<Crimeloc> response){
        r=findViewById(R.id.searchforcrecycler);
        adapter=new searchlocadapter(response,getApplicationContext());
        RecyclerView.LayoutManager layoutmanager=new LinearLayoutManager(searchforc.this);
        r.setLayoutManager(layoutmanager);
        r.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            boolean swipeBack;
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == RIGHT) {
                    adapter.addToFav(viewHolder.getAdapterPosition());
                }
            }
            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {
                if (swipeBack) {
                    swipeBack = false;
                    return 0;
                }
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }
            @Override
            public void onChildDraw(Canvas c,
                                    RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                searchlocadapter.viewholder vh=(searchlocadapter.viewholder)viewHolder;
                if(dX>=400)
                {
                    adapter.addToFav(vh.getAdapterPosition());
                }
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            private void setTouchListener(Canvas c,
                                          RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          float dX, float dY,
                                          int actionState, boolean isCurrentlyActive) {

                recyclerView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        swipeBack = event.getAction() == MotionEvent.ACTION_UP;
                        return false;
                    }
                });
            }
        }).attachToRecyclerView(r);
    }
}
