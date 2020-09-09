package com.meet.mogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Create_first extends AppCompatActivity {

     private EditText collegename,token;

     private FirebaseFirestore db=FirebaseFirestore.getInstance();

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_first);

        collegename=findViewById(R.id.collegecode);
        token=findViewById(R.id.token);

    }

    public void done(View v){

        if(collegename.getText().toString().isEmpty()){
            collegename.setError("Please write your collegecode ");
            Toast.makeText(Create_first.this, "Please write your collegecode !!!", Toast.LENGTH_SHORT).show();

        }else if (token.getText().toString().isEmpty()){
            token.setError("Please write your collegecode ");
            Toast.makeText(Create_first.this, "Please write token !!!", Toast.LENGTH_SHORT).show();


        }else {
            db.collection(collegename.getText().toString().trim()+"")
                    .document("others").get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {


                            if(documentSnapshot.exists()){
                                String token1=documentSnapshot.get("token").toString();


                                String token2=token.getText().toString().trim();

                                if(token1.equals(token2)){
                                    Toast.makeText(Create_first.this, "All is done!", Toast.LENGTH_SHORT).show();

                                    sp = getSharedPreferences(mypreference,
                                            Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sp.edit();
                                    editor.putString("mode","create");
                                    editor.putString("collegecode",collegename.getText().toString().trim()+"");

                                    editor.commit();
                                    Intent in=new Intent(Create_first.this,Create_second.class);
                                    startActivity(in);

                                }else {

                                    Toast.makeText(Create_first.this, "Please write correct token", Toast.LENGTH_SHORT).show();

                                }
                            }else {
                                Toast.makeText(Create_first.this, "Error Error!!", Toast.LENGTH_SHORT).show();

                            }



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Create_first.this, "Something error!!", Toast.LENGTH_SHORT).show();

                }
            });


        }



    }

}
