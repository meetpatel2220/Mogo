package com.meet.mogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adeptor_Join_forth extends RecyclerView.Adapter<Adeptor_Join_forth.ViewHolder> {

    Context fcontext;
    List<Model_Join_forth> fupload;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String itemuid;
    int i = 0;

  //  private List<Model_Join_forth> list=new ArrayList();

//    public void setDataSet(newList){
//
//        list.clear();
//        list.addAll(newList);
//        notifyDataSetChanged();
//
//    }

    public void add(Model_Join_forth s) {
        fupload.add(s);
    }



    public Adeptor_Join_forth(Context context, List<Model_Join_forth> user, String uid) {
        fcontext = context;
        fupload = user;
        itemuid = uid;

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
        sp = fcontext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        final String classcode1 = sp.getString("classcode", "");
        final String collegecode1 = sp.getString("collegecode", "");
        String collegename1 = sp.getString("collegename", "");


        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Map<String, String> map = new HashMap<>();
                    map.put("payment", "done");

//                String amount = editText.getText().toString();
//                String upi = editText1.getText().toString();

                String amount ="1";
                String upi = "jbbram681@okicici";

                Toast.makeText(fcontext, fupload.get(position).getUserid()+"", Toast.LENGTH_SHORT).show();

                String transactionNote = fupload.get(position).getName()+" paid "+"1 rs";
                String currencyUnit = "INR";
                Uri uri = Uri.parse( "upi://pay?pa=" + upi + "&pn=" + "any upi name" + "&tn=" + transactionNote + "&am=" + amount + "&cu=" + currencyUnit );
                Intent intent = new Intent();
                intent.setData( uri );
                //   intent.putExtra("userid",fupload.get(position).getUserid()+"");
                Intent chooser = Intent.createChooser( intent, "Pay with..." );

                ((Activity) fcontext).startActivityForResult( chooser, 1,null);

//                    db.collection(collegecode1 + "").document(classcode1 + "")
//                            .collection("item").document(itemuid + "").collection("user")
//                            .document(fupload.get(position).getUserid() + "")
//                            .set(map, SetOptions.merge());
//
//                    holder.payment.setText("done");


            }
        });



    }


    @Override
    public int getItemCount() {
        return fupload.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, payment, received;
        CardView cv;
        Button pay;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            payment = itemView.findViewById(R.id.payment);
            received = itemView.findViewById(R.id.received);

            pay = itemView.findViewById(R.id.b1);

        }
    }


}

