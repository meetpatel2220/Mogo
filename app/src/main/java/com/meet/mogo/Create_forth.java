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

public class Create_forth extends AppCompatActivity {

    private TextView hi;
    SharedPreferences sp;
    public static final String mypreference = "mypreference";


    private Adeptor_Create_forth adaptor;
    private FirestoreRecyclerAdapter adapter;
    private RecyclerView rv;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forth);

        rv = findViewById(R.id.recycle);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        Toast.makeText(Create_forth.this, "ok", Toast.LENGTH_SHORT).show();
//            sp = getSharedPreferences(mypreference,
//                    Context.MODE_PRIVATE);
//        if (sp.contains("collegecode") && sp.contains("classcode")) {
//            String classcode1 = sp.getString("classcode", "");
//            String collegecode1 = sp.getString("collegecode", "");


            Query query=db.collection("msu").document("manan")
                    .collection("item");
            FirestoreRecyclerOptions<Model_Create_forth> options=new FirestoreRecyclerOptions.Builder<Model_Create_forth>()
                    .setQuery(query,Model_Create_forth.class)
                    .build();
             adapter= new FirestoreRecyclerAdapter<Model_Create_forth, ProductViewHolder>(options) {
                @NonNull
                @Override
                public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_forth,
                            parent, false);

                    return new ProductViewHolder(v);
                }

                @Override
                protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Model_Create_forth model) {
                    holder.name.setText(model.getName());
                    holder.price.setText(model.getPrice());
                    holder.deadline.setText(model.getDeadline());
                    holder.deadlineend.setText(model.getDeadlineend());
                }
            };


//            if (sp.contains("collegecode") && sp.contains("classcode")) {
//                String classcode1 = sp.getString("classcode", "");
//                String collegecode1 = sp.getString("collegecode", "");
//
//                Query query = db.collection(collegecode1 + "").document(classcode1 + "")
//                        .collection("item");
//                FirestoreRecyclerOptions<Model_Create_forth> options = new FirestoreRecyclerOptions.Builder<Model_Create_forth>()
//                        .setQuery(query, Model_Create_forth.class)
//                        .build();
//                adaptor = new Adeptor_Create_forth(options);
//                rv.setAdapter(adaptor);
//
//            }
    }

//    private class ProductViewHolder extends RecyclerView.ViewHolder{
//        TextView name,price,deadline,deadlineend;
//        Button b1,b2;
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name=itemView.findViewById(R.id.name);
//            price=itemView.findViewById(R.id.price);
//            deadline=itemView.findViewById(R.id.deadline);
//            deadlineend=itemView.findViewById(R.id.deadlineend);
//            b1=itemView.findViewById(R.id.b1);
//
//            b2=itemView.findViewById(R.id.b2);
//
//        }
//    }
//    }
    private class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,deadline,deadlineend;
        Button b1,b2;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            deadline=itemView.findViewById(R.id.deadline);
            deadlineend=itemView.findViewById(R.id.deadlineend);
            b1=itemView.findViewById(R.id.b1);
            b2=itemView.findViewById(R.id.b2);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    adapter.stopListening();
//    }
}
