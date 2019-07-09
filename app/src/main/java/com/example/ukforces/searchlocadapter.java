package com.example.ukforces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class searchlocadapter extends RecyclerView.Adapter<searchlocadapter.viewholder>{
    @NonNull
    private List<Crimeloc> crime;
    Context context;
    DatabaseHelper dh;
    public searchlocadapter(@NonNull List<Crimeloc> crime, Context context) {
        this.crime = crime;
        this.context = context;
    }

    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchloclist,parent,false);
        viewholder holder= new viewholder(view);
        return holder;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        holder.locid.setText(crime.get(position).getId());
        holder.loccat.setText(crime.get(position).getCat());
    }
    public void addToFav(final int position)
    {
        dh=new DatabaseHelper(context);
        if(crime.get(position).isIsfav()) {
        }
            else{
            String lat,lon,si,sn,cat;
            if(crime.get(position).getLoc()==null){
                lat="";
                lon="";
                si="";
                sn="";
            }
            else{
                lat=crime.get(position).getLoc().getLat();
                lon=crime.get(position).getLoc().getLon();
                si=crime.get(position).getLoc().getStreet().getId();
                sn=crime.get(position).getLoc().getStreet().getName();
            }
            if(crime.get(position).getOut()==null)
                cat="";
            else
                cat=crime.get(position).getOut().getCat();
            boolean inserdata=dh.addData(crime.get(position).getId(),crime.get(position).getCat(),lat,lon,si,sn,cat,crime.get(position).getMon());
            if(inserdata) {
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                crime.get(position).isfav=true;
            }
            else
                Toast.makeText(context,"something went wrong",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public int getItemCount() {
        return crime.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView locid,loccat;
        RelativeLayout parentlayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            locid=itemView.findViewById(R.id.locid);
            loccat=itemView.findViewById(R.id.loccat);
            parentlayout=itemView.findViewById(R.id.loclistlayout);
            parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,disloccrime.class);
                    if(crime.get(getAdapterPosition()).getLoc()!=null){
                        intent.putExtra("lat",crime.get(getAdapterPosition()).getLoc().getLat());
                        intent.putExtra("lon",crime.get(getAdapterPosition()).getLoc().getLon());
                        intent.putExtra("streetid",crime.get(getAdapterPosition()).getLoc().getStreet().getId());
                        intent.putExtra("location",1);
                        intent.putExtra("streetname",crime.get(getAdapterPosition()).getLoc().getStreet().getName());}
                    else {
                        intent.putExtra("location",0);
                    }
                    intent.putExtra("date",crime.get(getAdapterPosition()).getMon());
                    intent.putExtra("id",crime.get(getAdapterPosition()).getId());
                    if(crime.get(getAdapterPosition()).getOut()!=null){
                        intent.putExtra("cat",crime.get(getAdapterPosition()).getOut().getCat());}
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}
