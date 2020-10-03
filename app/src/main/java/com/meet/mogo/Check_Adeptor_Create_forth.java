package com.meet.mogo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Check_Adeptor_Create_forth extends FirestoreRecyclerAdapter<Check_Model_Create_forth,Check_Adeptor_Create_forth.NoteHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Check_Adeptor_Create_forth(@NonNull FirestoreRecyclerOptions<Check_Model_Create_forth> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Check_Model_Create_forth model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.deadline.setText(model.getDeadline());
        holder.deadlineend.setText(model.getDeadlineend());
        holder.details.setText(model.getDetails());



    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_forth,
                parent, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView deadline;
        TextView deadlineend;
        TextView details;


        public NoteHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            deadline = itemView.findViewById(R.id.deadline);
            deadlineend = itemView.findViewById(R.id.deadlineend);
            details = itemView.findViewById(R.id.details);



        }
    }
}

