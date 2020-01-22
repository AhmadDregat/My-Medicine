package com.example.mymedicine;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MershamHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView merName, merPrice, merFreq;
    public ItemClickListner listner;
    public ImageView img;

    public MershamHandler(View itemView) {
        super(itemView);
        merName = itemView.findViewById(R.id.mer_name);
        merPrice = itemView.findViewById(R.id.mer_price);
        merFreq = itemView.findViewById(R.id.mer_freq);
        img = itemView.findViewById(R.id.curr_pic_med);

    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition());
    }

}