package com.example.mymedicine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MedHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView medName;
    public ItemClickListner listner;
    public Button selectMedBtn;

    public MedHandler(View itemView) {
        super(itemView);
        medName = itemView.findViewById(R.id.med_name);
        selectMedBtn = itemView.findViewById(R.id.select_med);
    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition());
    }

}