package com.meet.mogo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Join_second extends AppCompatActivity {

    private TextView classcode,collegename;
    private RecyclerView rv;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    private final List<Model_Create_forth> list_data = new ArrayList<>();

    private Adeptor_Join_two adaptor;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_second);

        classcode=findViewById(R.id.classcode);
        collegename=findViewById(R.id.collegename);
        rv=findViewById(R.id.recycle);

        rv.setLayoutManager(new LinearLayoutManager(Join_second.this));


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

            db.collection(collegecode1 + "").document(classcode1 + "")
                    .collection("item").whereEqualTo("delete","no").addSnapshotListener(Join_second.this, new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                    if (error != null) {
                        return;

                    }
           list_data.clear();
                    for (QueryDocumentSnapshot documentSnapshot : value) {

                        Model_Create_forth code = documentSnapshot.toObject(Model_Create_forth.class);
                        code.setItemid(documentSnapshot.getId());


                        list_data.add(code);


                    }

                    adaptor = new Adeptor_Join_two(Join_second.this, list_data);
                    rv.setAdapter(adaptor);

                }
            });



        }else {
            Toast.makeText(this, "Something error !! \nplease clear your cache memory and download again!!", Toast.LENGTH_SHORT).show();

        }

        }


    public void collegename(String collegecode) {

        db.collection(collegecode + "").document("others")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String collegename = documentSnapshot.get("collegename").toString();

                Toast.makeText(Join_second.this, "your collegename is " + collegename, Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Join_second.this, "Error Error !!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
