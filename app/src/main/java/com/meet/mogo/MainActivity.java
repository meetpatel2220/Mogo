package com.meet.mogo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    public static final String mypreference = "mypreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sp.contains("mode")) {


            if (sp.getString("mode", "").equals("create")) {

                Intent in = new Intent(MainActivity.this, Create_third.class);
                startActivity(in);
                finish();

            }


            if (sp.getString("mode", "").equals("join")) {

                Intent in = new Intent(MainActivity.this, Join_second.class);
                startActivity(in);
                finish();

            }

        }


    }

    public void join(View v) {

        Intent in = new Intent(MainActivity.this, Join_first.class);
        startActivity(in);

    }

    public void create(View v) {
        sp = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        Intent in = new Intent(MainActivity.this, Create_first.class);
        startActivity(in);

    }

}
