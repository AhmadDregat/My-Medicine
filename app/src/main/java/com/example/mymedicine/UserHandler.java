package com.example.mymedicine;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class UserHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView userName, emailuser;
    public ItemClickListner listner;
    public Button addnew;

    public UserHandler(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.user_name);
        System.out.println("hsdkjfha " + userName);
        emailuser = itemView.findViewById(R.id.email_user);
        addnew = itemView.findViewById(R.id.add_pre);
    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition());
    }

}
