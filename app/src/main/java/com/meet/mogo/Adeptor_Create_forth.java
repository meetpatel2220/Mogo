package com.meet.mogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adeptor_Create_forth extends RecyclerView.Adapter<Adeptor_Create_forth.ViewHolder> {

    Context fcontext;
    List<Model_Create_forth> fupload;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";
     FirebaseFirestore db = FirebaseFirestore.getInstance();

     String itemuid;

    public void add(Model_Create_forth s) {
        fupload.add(s);
    }
    public Adeptor_Create_forth(Context context, List<Model_Create_forth> user, String uid) {
        fcontext = context;
        fupload = user;
        itemuid=uid;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fcontext).inflate(R.layout.item_create_forth, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.itemname.setText(fupload.get(position).getName());
        holder.price.setText(fupload.get(position).getPrice());
        holder.deadline.setText(fupload.get(position).getDeadline());
        holder.deadlineend.setText(fupload.get(position).getDeadlineend());
        holder.details.setText(fupload.get(position).getDetails());


        sp = fcontext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

            final String classcode1 = sp.getString("classcode", "");
            final String collegecode1 = sp.getString("collegecode", "");
            String collegename1 = sp.getString("collegename", "");


            holder.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                }
            });





    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemname,price,deadline,deadlineend,details;

        Button pay;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemname=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            deadline=itemView.findViewById(R.id.deadline);
            deadlineend=itemView.findViewById(R.id.deadlineend);
            details=itemView.findViewById(R.id.details);
           pay=itemView.findViewById(R.id.b1);

        }
    }
}

