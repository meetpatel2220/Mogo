package com.meet.mogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Create_third extends AppCompatActivity {

    private TextView classcode;
    private EditText name, details, price, deadline;
    private ImageView imageview;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_third);

        classcode = findViewById(R.id.classcode);


        name = findViewById(R.id.name);
        details = findViewById(R.id.details);
        price = findViewById(R.id.price);
        deadline = findViewById(R.id.deadline);

    }

    @Override
    protected void onStart() {
        super.onStart();


        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("collegecode") && sp.contains("classcode")) {

            classcode.setText(sp.getString("classcode", ""));

        }
    }

    public void image(View v) {


    }

    public void done(View v) {

        if(name.getText().toString().isEmpty()){
            name.setError("Please write item name !!!");
            Toast.makeText(Create_third.this, "Please write item name !!!", Toast.LENGTH_SHORT).show();


        }else if(details.getText().toString().isEmpty()){
            details.setError("Please write item details !!!");
            Toast.makeText(Create_third.this, "Please write item details !!!", Toast.LENGTH_SHORT).show();

        }else if(price.getText().toString().isEmpty()){
            price.setError("Please write item price !!!");
            Toast.makeText(Create_third.this, "Please write item price !!!", Toast.LENGTH_SHORT).show();

        }else if(deadline.getText().toString().isEmpty()){
            deadline.setError("Please write item deadline !!!");
            Toast.makeText(Create_third.this, "Please write item deadline !!!", Toast.LENGTH_SHORT).show();

        }else {
            sp = getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);

            if (sp.contains("collegecode") && sp.contains("classcode")) {

                String classcode1 = sp.getString("classcode", "");
                String collegecode1 = sp.getString("collegecode", "");


                Map<String, Object> map = new HashMap<>();
                map.put("name", name.getText().toString());
                map.put("details", details.getText().toString());
                map.put("price", price.getText().toString());
                map.put("deadline", deadline.getText().toString());
                map.put("deadlineend", "no");
                map.put("delete", "no");


                db.collection(collegecode1 + "").document(classcode1 + "")
                        .collection("item").add(map)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(Create_third.this, "item added successfully", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Create_third.this, "Error Error!! Something problem to add item!!", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        }




    }

    public void allitem(View v) {



        Intent in = new Intent(Create_third.this, Create_forth.class);
        startActivity(in);


    }

    public void unroll(View v) {


        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("collegecode", "");
        editor.putString("classcode", "");
        editor.putString("mode", "");

        editor.commit();

        Intent in = new Intent(Create_third.this, MainActivity.class);
        startActivity(in);
    }
}
