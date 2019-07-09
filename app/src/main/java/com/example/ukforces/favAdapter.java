package com.example.ukforces;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class favAdapter extends RecyclerView.Adapter<favAdapter.viewholder> implements Filterable {
    @NonNull
    List<Crimeloc> crime,crimefull;
    Context context;

    public favAdapter(@NonNull List<Crimeloc> crime, Context context) {
        this.crime = crime;
        this.context = context;
        crimefull=new ArrayList<>(crime);
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.searchloclist,parent,false);
        viewholder holder=new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.locid.setText(crime.get(position).getId());
        holder.loccat.setText(crime.get(position).getCat());
    }

    @Override
    public int getItemCount() {
        return crime.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        RelativeLayout parentlayout;
        TextView locid,loccat;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            parentlayout=itemView.findViewById(R.id.loclistlayout);
            locid=itemView.findViewById(R.id.locid);
            loccat=itemView.findViewById(R.id.loccat);
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
    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Crimeloc> filteredlist=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredlist.addAll(crimefull);
            }
            else{
                String filterpattern=constraint.toString().toLowerCase().trim();
                for(Crimeloc c: crimefull){
                    if(c.getId().contains(filterpattern)){
                        filteredlist.add(c);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            crime.clear();
            crime.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
