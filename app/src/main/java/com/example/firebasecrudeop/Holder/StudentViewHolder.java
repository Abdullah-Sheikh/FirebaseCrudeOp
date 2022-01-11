package com.example.firebasecrudeop.Holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasecrudeop.Interface.ItemClickListner;
import com.example.firebasecrudeop.R;

public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nametext , cnictext;
    public ItemClickListner listner;
    public Button updateBtn , deleteBtn;



    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        cnictext = (TextView) itemView.findViewById(R.id.cnic_text);
        nametext = (TextView) itemView.findViewById(R.id.name_text);
        updateBtn = (Button) itemView.findViewById(R.id.update_data);
        deleteBtn = (Button) itemView.findViewById(R.id.delete_data);

    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
