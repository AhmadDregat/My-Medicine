package com.example.mymedicine.model;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymedicine.R;

import org.w3c.dom.Text;
public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public  TextView userName, emailuser, userphone;
    public ItemClickListner listner;
    public Button addnew ;
    public UserHolder(View itemView)
    {
        super(itemView);
        addnew    = itemView.findViewById(R.id.add_pre);
        userName  = itemView.findViewById(R.id.user_name);
        emailuser = itemView.findViewById(R.id.email_user);
        userphone = itemView.findViewById(R.id.user_phone);
    }
    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }
    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }

}
interface ItemClickListner{
    void onClick(View view, int position, boolean isLongClick);
}
