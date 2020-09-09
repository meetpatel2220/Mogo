package com.meet.mogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Create_second extends AppCompatActivity {

    private TextView collegename,collegecode;
    private EditText classcode;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    SharedPreferences sp;
    public static final String mypreference = "mypreference";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_second);

        collegename=findViewById(R.id.collegename);
        collegecode=findViewById(R.id.collegecode);

        classcode=findViewById(R.id.classcode);

        getcollegenamecode();

        }

    public void done(View v){


        if(classcode.getText().toString().isEmpty()){

            classcode.setError("Please write classcode !!!");
            Toast.makeText(Create_second.this, "Please write classcode !!!", Toast.LENGTH_SHORT).show();


        }else {
            sp = getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);


            Map<String,Object> map=new HashMap<>();
            map.put("collegecode",sp.getString("collegecode", ""));
            map.put("classcode",classcode.getText().toString().trim());



            db.collection("Code").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    sp = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("classcode",classcode.getText().toString().trim()+"");

                    editor.commit();

                    Toast.makeText(Create_second.this, "data added successfully", Toast.LENGTH_SHORT).show();

                    Intent in=new Intent(Create_second.this,Create_third.class);
                    startActivity(in);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Create_second.this, "Error Error !!", Toast.LENGTH_SHORT).show();

                }
            });


        }


    }

    public void getcollegenamecode(){

        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("collegecode")) {

            collegecode.setText(sp.getString("collegecode", ""));


        }

        db.collection(collegecode.getText().toString().trim()+"").document("others")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()) {
                    String collegename1 = documentSnapshot.get("collegename").toString();

                    collegename.setText(collegename1 + "");

                    sp = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("collegename",collegename1+"");

                    editor.commit();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Create_second.this, "something Error!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
