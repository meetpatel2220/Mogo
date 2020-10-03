package com.meet.mogo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION;

public class Adeptor_Create_forth extends FirestoreRecyclerAdapter<Model_Create_forth, Adeptor_Create_forth.NoteHolder> {
    private OnItemClickListener listener;


    public Adeptor_Create_forth(@NonNull FirestoreRecyclerOptions<Model_Create_forth> options) {
        super(options);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_forth,
                parent, false);
        return new NoteHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Model_Create_forth model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.deadline.setText(model.getDeadline());
        holder.deadlineend.setText(model.getDeadlineend());


    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        TextView name,price,deadline,deadlineend;
        Button b1,b2;
        public NoteHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            deadline=itemView.findViewById(R.id.deadline);
            deadlineend=itemView.findViewById(R.id.deadlineend);

            b1=itemView.findViewById(R.id.b1);

            b2=itemView.findViewById(R.id.b2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 int position = getAdapterPosition();
                 if (position != RecyclerView.NO_POSITION && listener != null) {
                     listener.onItemDeadline(getSnapshots().getSnapshot(position), position);
                 }

             }
         });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemDelete(getSnapshots().getSnapshot(position), position);
                    }

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
        void onItemDeadline(DocumentSnapshot documentSnapshot, int position);
        void onItemDelete(DocumentSnapshot documentSnapshot, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}

