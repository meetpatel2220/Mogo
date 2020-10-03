package com.meet.mogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Create_fifth extends AppCompatActivity {
    private TextView classcode, collegename, itemname;
    private RecyclerView rv;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    private final List<Model_Join_forth> list_data = new ArrayList<>();

    private Adeptor_Create_fifth adaptor;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fifth);

        classcode = findViewById(R.id.classcode);
        collegename = findViewById(R.id.collegename);
        itemname = findViewById(R.id.itemname);

        rv = findViewById(R.id.recycle);

        rv.setLayoutManager(new LinearLayoutManager(Create_fifth.this));


    }

    @Override
    protected void onStart() {
        super.onStart();


        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("collegecode") && sp.contains("classcode")) {
            String classcode1 = sp.getString("classcode", "");
            String collegecode1 = sp.getString("collegecode", "");
            String collegename1 = sp.getString("collegename", "");

            classcode.setText(classcode1);

            collegename.setText(collegename1);


            Intent in1=getIntent();
            String itemname1=in1.getStringExtra("itemname");
            final String itemuid1=in1.getStringExtra("itemuid");

            itemname.setText(itemname1+"");

            db.collection(collegecode1+"").document(classcode1+"")
                    .collection("item").document(itemuid1+"")
                    .collection("user").addSnapshotListener(Create_fifth.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                    if (error != null) {
                        return;

                    }

                    for (QueryDocumentSnapshot documentSnapshot : value) {

                        Model_Join_forth code = documentSnapshot.toObject(Model_Join_forth.class);
                        code.setUserid(documentSnapshot.getId());


                        list_data.add(code);


                    }

                    adaptor = new Adeptor_Create_fifth(Create_fifth.this, list_data,itemuid1);
                    rv.setAdapter(adaptor);


                }
            });


        }


    }


}