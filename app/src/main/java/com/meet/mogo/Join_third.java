package com.meet.mogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Join_third extends AppCompatActivity {

    private TextView gmailid;
    private EditText name,mobileno;
    private static final int REQUEST_CODE_EMAIL = 1;

    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_third);

        TextView itemname = findViewById(R.id.itemname);
        gmailid=findViewById(R.id.gmailid);

        name=findViewById(R.id.name);
        mobileno=findViewById(R.id.mobileno);

        Intent in1=getIntent();
        String itemuid=in1.getStringExtra("itemuid");
        String itemname1=in1.getStringExtra("itemname");


        itemname.setText(itemname1);

        //Toast.makeText(this, "information "+itemuid+"  "+itemname1, Toast.LENGTH_SHORT).show();


    }
    
    public void gmail(View v){

        try {
            Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                    new String[] { GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE }, false, null, null, null, null);
            startActivityForResult(intent, REQUEST_CODE_EMAIL);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Join_third.this, "Something Error", Toast.LENGTH_SHORT).show();
        }


    }

    public void done(View v){

        if(name.getText().toString().isEmpty()){
            name.setError("Please write your name !!!");
            Toast.makeText(Join_third.this, "Please write your name", Toast.LENGTH_SHORT).show();
        }
        else if(mobileno.getText().toString().isEmpty()){
            mobileno.setError("Please write your mobile number !!!");
            Toast.makeText(Join_third.this, "Please write your mobile number !!!", Toast.LENGTH_SHORT).show();
        }else if(gmailid.getText().toString().isEmpty()){
            mobileno.setError("Please select your gmail id !!!");
            Toast.makeText(Join_third.this, "Please select your gmail id !!!", Toast.LENGTH_SHORT).show();

        }else {


            sp = getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);

            if (sp.contains("collegecode") && sp.contains("classcode")) {
                String classcode1 = sp.getString("classcode", "");
                String collegecode1 = sp.getString("collegecode", "");

                Intent in1=getIntent();
                String itemuid=in1.getStringExtra("itemuid");
                String itemname1=in1.getStringExtra("itemname");

                Map<String,Object> map=new HashMap<>();
                map.put("name",name.getText().toString()+"");
                map.put("email",gmailid.getText().toString()+"");
                map.put("mobileno",mobileno.getText().toString()+"");
                map.put("payment","left");
                map.put("received","no");

                db.collection(collegecode1+"").document(classcode1+"")
                        .collection("item").document(itemuid+"")
                        .collection("user").add(map).addOnSuccessListener(Join_third.this, new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(Join_third.this, "All is done!!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(Join_third.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Join_third.this, "Error Error !!!!!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
    }
    public void allbuyers(View v){

        Intent in1=getIntent();
        String itemuid=in1.getStringExtra("itemuid");
        String itemname1=in1.getStringExtra("itemname");

        Intent in=new Intent(Join_third.this,Join_forth.class);
        in.putExtra("itemuid",itemuid);
        in.putExtra("itemname",itemname1);
        startActivity(in);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EMAIL && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            gmailid.setText(accountName);
            checkemail(accountName);

        }
    }


    public void checkemail(final String email){
        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("collegecode") && sp.contains("classcode")) {
            String classcode1 = sp.getString("classcode", "");
            String collegecode1 = sp.getString("collegecode", "");

            Intent in1 = getIntent();
            String itemuid = in1.getStringExtra("itemuid");
            String itemname1 = in1.getStringExtra("itemname");


            db.collection(collegecode1 + "").document(classcode1 + "")
                    .collection("item").document(itemuid + "")
                    .collection("user").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                        Model_Join_forth code = documentSnapshot.toObject(Model_Join_forth.class);
                        code.setUserid(documentSnapshot.getId());

                        String email1=code.getEmail();

                        if(email1.equals(gmailid.getText().toString())){
                            gmailid.setText(null);
                            gmailid.setError("Please select another email id ,this is already in use for this item !!!");

                            Toast.makeText(Join_third.this, "Please select another email id ,this is already in use for this item !!!", Toast.LENGTH_SHORT).show();
                        }else {


                        }


                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Join_third.this, "Something Error !!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
