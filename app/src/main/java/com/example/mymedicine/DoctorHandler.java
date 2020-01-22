package com.example.mymedicine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DoctorHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView doctor_name;
    public ItemClickListner listner;
    public Button select_doc;


    public DoctorHandler(View itemView) {
        super(itemView);
        doctor_name = itemView.findViewById(R.id.doc_name);
        select_doc = itemView.findViewById(R.id.select_doctor);
    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        System.out.println("test");
        listner.onClick(view, getAdapterPosition());
    }

}