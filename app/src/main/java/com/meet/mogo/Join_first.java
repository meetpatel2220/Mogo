package com.meet.mogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Join_first extends AppCompatActivity {

    private EditText classcode;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_first);

        classcode = findViewById(R.id.classcode);

    }

    public void done(View v) {

        if(classcode.getText().toString().isEmpty()){

            classcode.setError("Please write classcode !!! ");
            Toast.makeText(Join_first.this, "Please write classcode", Toast.LENGTH_SHORT).show();
        }else {


            db.collection("Code").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {


                        Model_Join_first code = documentSnapshot.toObject(Model_Join_first.class);

                        String classcodestring = code.getClasscode();

                        Toast.makeText(Join_first.this, classcodestring, Toast.LENGTH_SHORT).show();
                        if (classcodestring.equals(classcode.getText().toString())) {

                            collegename(code.getCollegecode().toString());


                            sp = getSharedPreferences(mypreference,
                                    Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("classcode", classcode.getText().toString().trim() + "");
                            editor.putString("mode", "join");
                            editor.putString("collegecode", code.getCollegecode().toString() + "");

                            editor.commit();

                            Intent in = new Intent(Join_first.this, Join_second.class);
                            startActivity(in);
                            finish();


                        } else {

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

    public void collegename(String collegecode) {

        db.collection(collegecode + "").document("others")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String collegename = documentSnapshot.get("collegename").toString();

                sp = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("collegename", collegename+ "");

                editor.commit();

                Toast.makeText(Join_first.this, "your collegename is " + collegename, Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Join_first.this, "Error Error !!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
