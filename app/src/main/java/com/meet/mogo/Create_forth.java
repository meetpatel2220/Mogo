package com.meet.mogo;
import android.content.Context;
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
public class Create_forth extends AppCompatActivity {

    private TextView hi;
    SharedPreferences sp;
    public static final String mypreference = "mypreference";
    private RecyclerView rv;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final List<Model_Create_forth> list_data = new ArrayList<>();
    private Adeptor_Create_forth adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_forth );
        rv = findViewById( R.id.recycle );
        rv.setHasFixedSize( true );
        rv.setLayoutManager( new LinearLayoutManager( this ) );
        //  rv.setAdapter(adapter);
        sp = getSharedPreferences( mypreference,
                                   Context.MODE_PRIVATE );
        if (sp.contains( "collegecode" ) && sp.contains( "classcode" )) {
            String classcode1 = sp.getString( "classcode", "" );
            String collegecode1 = sp.getString( "collegecode", "" );
            String collegename1 = sp.getString( "collegename", "" );
            db.collection( collegecode1 + "" ).document( classcode1 + "" )
                    .collection( "item" ).whereEqualTo( "delete", "no" ).addSnapshotListener( new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        return;

                    }
                    list_data.clear();
                    assert value != null;
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        Model_Create_forth code = documentSnapshot.toObject( Model_Create_forth.class );
                        code.setItemid( documentSnapshot.getId() );
                        list_data.add( code );

                    }
                    adaptor = new Adeptor_Create_forth( Create_forth.this, list_data );
                    rv.setAdapter( adaptor );

                }
            } );

        }

    }

}
