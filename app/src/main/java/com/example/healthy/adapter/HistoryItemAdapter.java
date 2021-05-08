package com.example.healthy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthy.R;
import com.example.healthy.models.Item;

import java.util.ArrayList;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ViewHolder> {

    private ArrayList<Item> historyItems;

    public HistoryItemAdapter(ArrayList<Item> historyItems){
        this.historyItems = historyItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.type.setText(historyItems.get(position).getActivityType());
        holder.date.setText(historyItems.get(position).getDate());
        holder.information.setText(historyItems.get(position).getInformation());
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        TextView date;
        TextView information;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.history_list_type);
            date = itemView.findViewById(R.id.history_list_date);
            information = itemView.findViewById(R.id.history_list_information);
        }
    }


}
