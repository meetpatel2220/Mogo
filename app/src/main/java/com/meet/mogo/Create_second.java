package com.meet.mogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Create_second extends AppCompatActivity {

    private TextView collegename, collegecode;
    private EditText classcode;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sp;
    public static final String mypreference = "mypreference";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_second);

        collegename = findViewById(R.id.collegename);
        collegecode = findViewById(R.id.collegecode);

        classcode = findViewById(R.id.classcode);

        getcollegenamecode();

    }

    public void recreate(View v) {

        if (classcode.getText().toString().isEmpty() || classcode.getText().toString().equals("others")) {

            classcode.setError("Please write classcode !!!");
            Toast.makeText(Create_second.this, "Please write classcode !!!", Toast.LENGTH_SHORT).show();


        } else if (classcode.getText().toString().equals("others")) {

            classcode.setError("You can not take others as a classname !!");
            Toast.makeText(Create_second.this, "Please write classcode !!!", Toast.LENGTH_SHORT).show();

        } else {

            db.collection("Code").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {


                        Model_Join_first code = documentSnapshot.toObject(Model_Join_first.class);

                        String classcodestring = code.getClasscode();

                        if (classcodestring.equals(classcode.getText().toString())) {

                            sp = getSharedPreferences(mypreference,
                                    Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("classcode", classcode.getText().toString().trim() + "");

                            editor.commit();

                            Toast.makeText(Create_second.this, "data added successfully", Toast.LENGTH_SHORT).show();

                            Intent in = new Intent(Create_second.this, Create_third.class);
                            startActivity(in);


                        }

                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                }
            });


        }


    }

    public void done(View v) {


        checkclasscode();

        if (classcode.getText().toString().isEmpty() || classcode.getText().toString().equals("others")) {

            classcode.setError("Please write classcode !!!");
            Toast.makeText(Create_second.this, "Please write classcode !!!", Toast.LENGTH_SHORT).show();


        } else if (classcode.getText().toString().equals("others")) {

            classcode.setError("You can not take others as a classname !!");
            Toast.makeText(Create_second.this, "Please write classcode !!!", Toast.LENGTH_SHORT).show();

        } else {
            sp = getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);


            Map<String, Object> map = new HashMap<>();
            map.put("collegecode", sp.getString("collegecode", ""));
            map.put("classcode", classcode.getText().toString().trim());


            db.collection("Code").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    sp = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("classcode", classcode.getText().toString().trim() + "");

                    editor.commit();

                    Toast.makeText(Create_second.this, "data added successfully", Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(Create_second.this, Create_third.class);
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

    public void checkclasscode() {


        db.collection("Code").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                int i = 0;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {


                    Model_Join_first code = documentSnapshot.toObject(Model_Join_first.class);

                    String classcodestring = code.getClasscode();

                    if (classcodestring.equals(classcode.getText().toString())) {

                        classcode.setText("");
                        classcode.setHint("write here new classcode");
                        classcode.setError("this classcode is already exist");

                        Toast.makeText(Create_second.this, "Please change your classcode ", Toast.LENGTH_SHORT).show();


                    }

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });

    }

    public void getcollegenamecode() {

        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("collegecode")) {

            collegecode.setText(sp.getString("collegecode", ""));


        }

        db.collection(collegecode.getText().toString().trim() + "").document("others")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    String collegename1 = documentSnapshot.get("collegename").toString();

                    collegename.setText(collegename1 + "");

                    sp = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("collegename", collegename1 + "");

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
