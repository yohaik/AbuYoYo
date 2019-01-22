package com.HaHa.abuyoyo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.HaHa.abuyoyo.model.backend.Backend;
import com.HaHa.abuyoyo.model.backend.BackendFactory;
import com.HaHa.abuyoyo.model.entities.Trip;
import com.HaHa.abuyoyo.model.entities.mTrip;

public class TripSearchActivity extends AppCompatActivity {

    private String fullName;
    private TextView mainMessage;

    Button cancelTrip;
    Backend dataBase = BackendFactory.getBackend();


    SharedPreferences prefs;


    final String WAIT_FOR_DRIVER = "Your Driver will come Shortly!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_search);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            fullName = extras.getString("fullName");
            // and get whatever type user account id is
        }

        Log.d("Abu","Name intent: " +fullName);


        mainMessage = findViewById(R.id.message_for_client);

        mainMessage.setText(fullName+",\n"+ WAIT_FOR_DRIVER);

        cancelTrip = findViewById(R.id.cancelTripButton);

        cancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;

                builder = new AlertDialog.Builder(TripSearchActivity.this);
                builder.setTitle("Cancel Trip")
                        .setMessage("Are you sure you want to cancel this trip?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(new Intent(TripSearchActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }
}
