package com.HaHa.abuyoyo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class  LoginActivity extends AppCompatActivity {

    public static final String ABU_PREFS = "AbuPrefs";
    public static final String DISPLAY_EMAIL = "email";

    private FirebaseAuth mAuth;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Launch Spalsh screen
        setTheme(R.style.AppTheme);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Connect the objects for screen
        mEmailView =  findViewById(R.id.email);
        mPasswordView =  findViewById(R.id.password);
        prefs = getSharedPreferences(ABU_PREFS,0);
        if (prefs.contains(DISPLAY_EMAIL))
            mEmailView.setText(prefs.getString("email", ""));

        // Activate Firebase Authentication
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == 100 || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    // Executed when Sign in button pressed
    public void signInExistingUser(View v)   {
        attemptLogin();
    }

    // Executed when Register button pressed
    public void registerNewUser(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(intent);
    }

    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.equals("")||password.equals(""))
            return;
        else {
            Toast.makeText(this,"Login in progress....",Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("AbuYoYo", "signInWithEmailAndPassword() OnCompplete: "+ task.isSuccessful());

                if(!task.isSuccessful())
                {
                    Log.d("AbuYoYo", "Problem signing in: "+ task.getException());
                    showErrorDialog("There was a problem signing in");
                }
                else{
                    String email = mEmailView.getText().toString();
                    SharedPreferences prefs = getSharedPreferences(ABU_PREFS,0);
                    prefs.edit().putString(DISPLAY_EMAIL, email).apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        });
    }

    private void showErrorDialog(String message){

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


}
