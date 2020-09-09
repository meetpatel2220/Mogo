package com.meet.mogo;


import android.content.Context;
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

public class Adeptor_Create_fifth extends RecyclerView.Adapter<Adeptor_Create_fifth.ViewHolder> {

    Context fcontext;
    List<Model_Join_forth> fupload;

    public void add(Model_Join_forth s) {
        fupload.add(s);
    }
    public Adeptor_Create_fifth(Context context, List<Model_Join_forth> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fcontext).inflate(R.layout.item_create_fifth, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(fupload.get(position).getName());
        holder.email.setText(fupload.get(position).getEmail());
        holder.payment.setText(fupload.get(position).getPayment());
        holder.received.setText(fupload.get(position).getReceived());

        holder.b1receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(fcontext, "user has got this item", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,email,payment,received;

        Button b1receive;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            payment=itemView.findViewById(R.id.payment);
            received=itemView.findViewById(R.id.received);

            b1receive=itemView.findViewById(R.id.b1);

        }
    }
}

