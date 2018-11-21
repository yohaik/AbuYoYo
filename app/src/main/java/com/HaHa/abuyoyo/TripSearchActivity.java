package com.HaHa.abuyoyo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class TripSearchActivity extends AppCompatActivity {

    private String fullName;
    private TextView mainMessage;
    final String WAIT_FOR_DRIVER = "Your Driver will come Shortly!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_search);

        Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();

        String fullName2 = intent.getStringExtra("fullName");
        if (extras != null) {
            fullName = extras.getString("fullName");
            // and get whatever type user account id is
        }

        Log.d("Abu","Name intent: " +fullName);
        Log.d("Abu","Name bundle: " +fullName2);


        mainMessage = findViewById(R.id.message_for_client);

        mainMessage.setText(fullName+",\n"+ WAIT_FOR_DRIVER);



    }
}
