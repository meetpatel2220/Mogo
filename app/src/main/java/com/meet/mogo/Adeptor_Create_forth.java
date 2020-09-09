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

     FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sp;
    public static final String mypreference = "mypreference";


    public void add(Model_Create_forth s) {
        fupload.add(s);
    }
    public Adeptor_Create_forth(Context context, List<Model_Create_forth> user) {
        fcontext = context;
        fupload = user;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fcontext).inflate(R.layout.item_create_forth, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(fupload.get(position).getName());
        holder.price.setText(fupload.get(position).getPrice());
        holder.deadline.setText(fupload.get(position).getDeadline());
        holder.deadlineend.setText(fupload.get(position).getDeadlineend());

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(fcontext, "item received this ", Toast.LENGTH_SHORT).show();

                sp = fcontext.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                if (sp.contains("collegecode") && sp.contains("classcode")) {
                    String classcode1 = sp.getString("classcode", "");
                    String collegecode1 = sp.getString("collegecode", "");
                    String collegename1 = sp.getString("collegename", "");

                    Map<String,Object> map=new HashMap<>();
                    map.put("deadlineend","yes");
                    db.collection(collegecode1 + "").document(classcode1 + "")
                            .collection("item").document(fupload.get(position).getItemid())
                            .set(map, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(fcontext, fupload.get(position).getName()+" 's deadline is end ", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(fcontext, "something Error !! Please try again", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in=new Intent(fcontext,Create_fifth.class);
                in.putExtra("itemuid",fupload.get(position).getItemid());
                in.putExtra("itemname",fupload.get(position).getName());
                fcontext.startActivity(in);
                ((Activity)fcontext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,price,deadline,deadlineend;
        Button b1;
        CardView cv;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            deadline=itemView.findViewById(R.id.deadline);
            deadlineend=itemView.findViewById(R.id.deadlineend);

            b1=itemView.findViewById(R.id.b1);

            cv=itemView.findViewById(R.id.cv);
        }
    }


}

