package com.HaHa.abuyoyo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Object on main activity screen
    private EditText mFullName = findViewById(R.id.fulllNameEditText);
    private EditText mPhoneNumber = findViewById(R.id.phoneNumberEditText);
    private EditText mEmail = findViewById(R.id.emailEditText);
    private EditText mDestination = findViewById(R.id.destinationEditText);
    private Button mLoadMeButton = (Button) findViewById(R.id.loadMeonButton);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = mFullName.getText().toString();
                String phone = mPhoneNumber.getText().toString();
                String email = mEmail.getText().toString();
                String destination = mDestination.getText().toString();



            }


        });
    }

}
