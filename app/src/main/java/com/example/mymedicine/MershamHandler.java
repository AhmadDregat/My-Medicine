package com.example.mymedicine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MershamHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView merName, merPrice, merFreq;
    public ItemClickListner listner;
    public Button selectedMerName;

    public MershamHandler(View itemView) {
        super(itemView);
        merName = itemView.findViewById(R.id.mer_name);
        merPrice = itemView.findViewById(R.id.mer_price);
        merFreq = itemView.findViewById(R.id.mer_freq);
        selectedMerName = itemView.findViewById(R.id.mer_btn);
    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition());
    }

}