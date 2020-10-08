package com.meet.mogo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Create_forth extends AppCompatActivity {

    private TextView hi;
    SharedPreferences sp;
    public static final String mypreference = "mypreference";




    private RecyclerView rv;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final List<Model_Join_forth> list_data = new ArrayList<>();

    private Adeptor_Join_forth adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forth);

        rv = findViewById(R.id.recycle);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
      //  rv.setAdapter(adapter);




    }

}
