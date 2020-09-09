package com.meet.mogo;

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

public class Adeptor_Join_forth extends RecyclerView.Adapter<Adeptor_Join_forth.ViewHolder> {

    Context fcontext;
    List<Model_Join_forth> fupload;

    public void add(Model_Join_forth s) {
        fupload.add(s);
    }
    public Adeptor_Join_forth(Context context, List<Model_Join_forth> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fcontext).inflate(R.layout.item_join_forth, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(fupload.get(position).getName());
        holder.email.setText(fupload.get(position).getEmail());
        holder.payment.setText(fupload.get(position).getPayment());
        holder.received.setText(fupload.get(position).getReceived());

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(fcontext, "pay  xx/- rs", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,payment,received;
        CardView cv;
        Button pay;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            payment=itemView.findViewById(R.id.payment);
            received=itemView.findViewById(R.id.received);

           pay=itemView.findViewById(R.id.b1);

        }
    }
}

