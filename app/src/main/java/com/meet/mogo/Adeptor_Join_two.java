package com.meet.mogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adeptor_Join_two extends RecyclerView.Adapter<Adeptor_Join_two.ViewHolder> {

    Context fcontext;
    List<Model_Create_forth> fupload;

    public void add(Model_Create_forth s) {
        fupload.add(s);
    }
    public Adeptor_Join_two(Context context, List<Model_Create_forth> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fcontext).inflate(R.layout.item_join_two, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(fupload.get(position).getName());
        holder.price.setText(fupload.get(position).getPrice());
        holder.deadline.setText(fupload.get(position).getDeadline());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(fcontext,Join_third.class);
                in.putExtra("itemuid",fupload.get(position).getItemid());
                in.putExtra("itemname",fupload.get(position).getName());
                fcontext.startActivity(in);
                ((Activity)fcontext).finish();
               // Toast.makeText(fcontext, "clicked"+fupload.get(position).getName()+fupload.get(position).getPrice()
                 //       +fupload.get(position).getDeadline(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,deadline;
        CardView cv;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            deadline=itemView.findViewById(R.id.deadline);
            cv=itemView.findViewById(R.id.cv);

        }
    }
}

