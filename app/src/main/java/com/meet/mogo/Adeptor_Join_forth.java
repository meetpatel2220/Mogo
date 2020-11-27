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
import com.google.firebase.firestore.DocumentSnapshot;
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
    FirebaseFirestore db1 = FirebaseFirestore.getInstance();

    String itemuid;



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
        holder.email.setText(fupload.get(position).getMobileno());
        holder.payment.setText(fupload.get(position).getPayment());
        holder.received.setText(fupload.get(position).getReceived());
        sp = fcontext.getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        final String classcode1 = sp.getString("classcode", "");
        final String collegecode1 = sp.getString("collegecode", "");
        String collegename1 = sp.getString("collegename", "");

        //check payment done or not
        db.collection(collegecode1 + "").document(classcode1 + "")
                            .collection("item").document(itemuid + "").collection("user")
                            .document(fupload.get(position).getUserid() + "")
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

           String payment=documentSnapshot.getString("payment");


           if(payment.equals("done (online)") || payment.equals("done (offline)")){

               holder.pay.setVisibility(View.GONE);

           }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });



        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Map<String, String> map = new HashMap<>();
                    map.put("payment", "done");


                    //saving userid and itemid
                sp = fcontext.getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userid", fupload.get(position).getUserid()+"");
                editor.putString("itemid", itemuid+"");
                editor.commit();

                String upi="aditya2211desai@oksbi";
                String amount="1";
                String transactionNote = fupload.get(position).getName()+" paid "+amount+" rs";
                String currencyUnit = "INR";

                Uri uri = Uri.parse("upi://pay").buildUpon()
                        .appendQueryParameter("pa", "9099782442-2@okbizaxis")  // google pay business id
                        .appendQueryParameter("pn", "meet")
                        .appendQueryParameter("mc", "")            /// 1st param - use it (it was commented on my earlier tutorial)
                        //.appendQueryParameter("tid", "02125412")
                        .appendQueryParameter("tr", "25584584")   /// 2nd param - use it (it was commented on my earlier tutorial)
                        .appendQueryParameter("tn", "write anything")
                        .appendQueryParameter("am", amount)
                        .appendQueryParameter("cu", "INR")
                        //.appendQueryParameter("refUrl", "blueapp")
                        .build();
               // Uri uri = Uri.parse( "upi://pay?pa=" + upi + "&pn=" + "any upi name" + "&tn=" + transactionNote + "&am=" + amount + "&cu=" + currencyUnit );
                Intent intent = new Intent();
                intent.setData( uri );
                intent.setPackage("com.google.android.apps.nbu.paisa.user");
                Intent chooser = Intent.createChooser( intent, "Pay with..." );


                ((Activity) fcontext).startActivityForResult( chooser, 1,null);


//                //get amount
//
//                db.collection(collegecode1 + "").document(classcode1 + "")
//                           .collection("item").document(itemuid + "").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        final String amount=documentSnapshot.getString("price");
//
//                        //get upi
//                 db1.collection(collegecode1+"").document("others").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                     @Override
//                     public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                         String upi=documentSnapshot.getString("upi");
//
//
//                       //  Toast.makeText(fcontext, amount+upi+"", Toast.LENGTH_SHORT).show();
//                        // String amount="1";
//                         //String upi=documentSnapshot.getString("upi");
//
//                         String transactionNote = fupload.get(position).getName()+" paid "+amount+" rs";
//                         String currencyUnit = "INR";
//                         Uri uri = Uri.parse( "upi://pay?pa=" + upi + "&pn=" + "any upi name" + "&tn=" + transactionNote + "&am=" + amount + "&cu=" + currencyUnit );
//                         Intent intent = new Intent();
//                         intent.setData( uri );
//                         intent.setPackage("com.google.android.apps.nbu.paisa.user");
//                         Intent chooser = Intent.createChooser( intent, "Pay with..." );
//
//
//                         ((Activity) fcontext).startActivityForResult( chooser, 1,null);
//
//
//                     }
//                 });
//
//
//
//
//                    }
//                });

             //   String amount ="1";
              //  String upi = "jbbram681@okicici";




           //     Toast.makeText(fcontext, fupload.get(position).getUserid()+"", Toast.LENGTH_SHORT).show();


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

