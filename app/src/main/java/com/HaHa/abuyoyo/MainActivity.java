package com.HaHa.abuyoyo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class MainActivity extends AppCompatActivity {

    // Object on main activity screen
    private EditText mFullName;
    private EditText mPhoneNumber;
    private EditText mEmail;
    private EditText mDestination;
    private PlaceAutocompleteFragment tripDest;

    Location locationDest = new Location("Dest");//= new Location(from);

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;

// Define a listener that responds to location updates
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFullName = findViewById(R.id.fulllNameEditText);
        mPhoneNumber = findViewById(R.id.phoneNumberEditText);
        mEmail = findViewById(R.id.emailEditText);
        tripDest = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.destinationEditText);
        tripDest.setHint(getString(R.string.trip_destination_hint));
        Button mLoadMeButton = findViewById(R.id.loadMeonButton);

        tripDest.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationDest.setLatitude(place.getLatLng().latitude);
                locationDest.setLongitude(place.getLatLng().longitude);
            }

            @Override
            public void onError(Status status) {

            }
        });

        mLoadMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = mFullName.getText().toString();
                String phone = mPhoneNumber.getText().toString();
                String email = mEmail.getText().toString();
                String destination = mDestination.getText().toString();


            }
        });

        mFullName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
    }

}
