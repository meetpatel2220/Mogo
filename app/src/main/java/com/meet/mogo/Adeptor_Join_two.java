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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Adeptor_Join_two extends RecyclerView.Adapter<Adeptor_Join_two.ViewHolder> {

    Context fcontext;
    List<Model_Create_forth> fupload;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();



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

                sp = fcontext.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);

                if (sp.contains("collegecode") && sp.contains("classcode")) {
                    String classcode1 = sp.getString("classcode", "");
                    String collegecode1 = sp.getString("collegecode", "");
                    String collegename1 = sp.getString("collegename", "");

                    db.collection(collegecode1 + "").document(classcode1 + "")
//                            .collection("item").document(fupload.get(position).getItemid())
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            String dead=documentSnapshot.get("deadlineend").toString();

                            if(dead.equals("yes")){

                                Intent in=new Intent(fcontext,Join_forth.class);
//                                in.putExtra("itemuid",fupload.get(position).getItemid());
                                in.putExtra("itemname",fupload.get(position).getName());
                                fcontext.startActivity(in);

                            }
                            else {
                                Intent in=new Intent(fcontext,Join_third.class);
//                                in.putExtra("itemuid",fupload.get(position).getItemid());
                                in.putExtra("itemname",fupload.get(position).getName());
                                fcontext.startActivity(in);

                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(fcontext, "Something Problem !!!", Toast.LENGTH_SHORT).show();
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

