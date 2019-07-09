package com.example.ukforces;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Forces_Adapter extends RecyclerView.Adapter<Forces_Adapter.viewholder> implements Filterable {
    @NonNull
    private List<Forces> forces;
    private List<Forces> forcesfull;
    Context context;

    public Forces_Adapter(@NonNull List<Forces> forces,Context context) {
        this.forces = forces;
        this.context=context;
        forcesfull=new ArrayList<>(forces);
    }
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_force_list,parent,false);
        viewholder holder=new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        holder.forceid.setText(forces.get(position).getId());
        holder.forcename.setText(forces.get(position).getName());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,specificforce.class);
                intent.putExtra("id",forces.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forces.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
      TextView forceid,forcename;
      RelativeLayout parentlayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            forceid=itemView.findViewById(R.id.forceid);
            forcename=itemView.findViewById(R.id.forcename);
            parentlayout=itemView.findViewById(R.id.flayout);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Forces> filteredlist=new ArrayList<>();
            if(constraint==null||constraint.length()==0){
              filteredlist.addAll(forcesfull);
            }
            else{
                String filterpattern=constraint.toString().toLowerCase().trim();
                for(Forces f: forcesfull){
                    if(f.getId().contains(filterpattern)){
                        filteredlist.add(f);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           forces.clear();
           forces.addAll((List)results.values);
           notifyDataSetChanged();
        }
    };
}
