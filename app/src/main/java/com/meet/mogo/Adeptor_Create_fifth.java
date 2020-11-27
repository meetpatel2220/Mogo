package com.meet.mogo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class Adeptor_Create_fifth extends RecyclerView.Adapter<Adeptor_Create_fifth.ViewHolder> {

    Context fcontext;
    List<Model_Join_forth> fupload;
    String itemid1;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    public void add(Model_Join_forth s) {
        fupload.add(s);
    }
    public Adeptor_Create_fifth(Context context, List<Model_Join_forth> user,String itemid) {
        fcontext = context;
        fupload = user;
        itemid1=itemid;
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
        holder.email.setText(fupload.get(position).getMobileno());
        holder.payment.setText(fupload.get(position).getPayment());
        holder.received.setText(fupload.get(position).getReceived());

        holder.b1receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sp = fcontext.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                if (sp.contains("collegecode") && sp.contains("classcode")) {
                    String classcode1 = sp.getString("classcode", "");
                    String collegecode1 = sp.getString("collegecode", "");
                    String collegename1 = sp.getString("collegename", "");

                    Map<String,Object> map=new HashMap<>();
                    map.put("received","yes");

                    db.collection(collegecode1 + "").document(classcode1 + "")
                            .collection("item").document(itemid1+"")
                            .collection("user").document(fupload.get(position).getUserid())
                            .set(map, SetOptions.merge()).addOnSuccessListener(((Activity) fcontext), new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(fcontext, fupload.get(position).getName()+" has got item", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(((Activity) fcontext), new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(fcontext, "Something Error !!!", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });


        holder.b2PayOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sp = fcontext.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                if (sp.contains("collegecode") && sp.contains("classcode")) {
                    String classcode1 = sp.getString("classcode", "");
                    String collegecode1 = sp.getString("collegecode", "");
                    String collegename1 = sp.getString("collegename", "");

                    Map<String, Object> map = new HashMap<>();
                    map.put("payment", "done (offline)");

                    db.collection(collegecode1 + "").document(classcode1 + "")
                            .collection("item").document(itemid1 + "")
                            .collection("user").document(fupload.get(position).getUserid())
                            .set(map, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(fcontext, fupload.get(position).getName()+"'s payment done", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(fcontext, "Something Error !! ", Toast.LENGTH_SHORT).show();

                        }
                    });


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,email,payment,received;

        Button b1receive,b2PayOffline;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            payment=itemView.findViewById(R.id.payment);
            received=itemView.findViewById(R.id.received);

            b1receive=itemView.findViewById(R.id.b1);
            b2PayOffline=itemView.findViewById(R.id.b2);




        }
    }
}

