package com.HaHa.abuyoyo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

import com.HaHa.abuyoyo.model.backend.AddTripAsynsTask;
import com.HaHa.abuyoyo.model.backend.Backend;
import com.HaHa.abuyoyo.model.backend.BackendFactory;
import com.HaHa.abuyoyo.model.entities.Trip;
import com.HaHa.abuyoyo.model.entities.mTrip;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.HaHa.abuyoyo.RegisterActivity.CHAT_PREFS;
import static com.HaHa.abuyoyo.RegisterActivity.DISPLAY_NAME_KEY;
import static com.HaHa.abuyoyo.RegisterActivity.DISPLAY_PHONE;

public class MainActivity extends AppCompatActivity {

    // Object on main activity screen
    private EditText mFullName;
    private EditText mPhoneNumber;
    private EditText mEmail;
    private PlaceAutocompleteFragment tripDest;
    private PlaceAutocompleteFragment tripOrig;

    private Button mLoadMeButton;
    private String mLocation;
    private String mDestination;
    private String mOrigin;
    private  Trip trip;
    Location locationDest = new Location("Dest");//= new Location(from);
    Location locationOrig = new Location("Origin");
    Backend dataBase;
    Boolean success;

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;
    //Define a listener that responds to location updates
    LocationListener locationListener;

    //class AsynsTask
    AddTripAsynsTask addTripAsynsTask;

    private void fineViews(){
       // mFullName =  findViewById(R.id.fulllNameEditText);
       // mPhoneNumber =  findViewById(R.id.phoneNumberEditText);
       // mEmail =  findViewById(R.id.emailEditText);
        tripDest = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.destinationFragment);
        tripOrig = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.originFragment);
        mLoadMeButton = (Button) findViewById(R.id.loadMeOnButton);
        mLoadMeButton.setEnabled(false);

        tripDest.setHint(getString(R.string.trip_destination_hint));
        tripOrig.setHint(getString(R.string.origin_hint));

        //srt listener autocomplet
        tripDest.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationDest.setLatitude(place.getLatLng().latitude);
                locationDest.setLongitude(place.getLatLng().longitude);
                mDestination = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {

            }
        });

        tripOrig.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationOrig.setLatitude(place.getLatLng().latitude);
                locationOrig.setLongitude(place.getLatLng().longitude);
                mOrigin = place.getAddress().toString();
                mLoadMeButton.setEnabled(true);
                locationManager.removeUpdates(locationListener);
            }

            @Override
            public void onError(Status status) {

            }
        });

        mLoadMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trip = createTripFromFields();

                Log.d("Abu","Name: " +trip.getPassengerName());
                dataBase = BackendFactory.getBackend();

               addTripAsynsTask = new AddTripAsynsTask(new AddTripAsynsTask.AddTripAsynsTaskFunction(){
                   @Override
                   public void onPreExecute() {
                       mLoadMeButton.setEnabled(false);
                       }

                   @Override
                   public void onPostExecute() {
                       mLoadMeButton.setEnabled(true);
                   }

                   @Override
                   public void doInBackground(final Trip trip) {
                       //success = true;
                       dataBase.addRequest(trip, new Backend.Action<Void>() {
                           @Override
                           public void onSuccess(Void obj) {
                               onProgress("upload trip data", 99);
                               Intent intent = new Intent(MainActivity.this, TripSearchActivity.class);
                               intent.putExtra("fullName", trip.getPassengerName());
                               intent.putExtra("phone", trip.getPassengerPhone());
                               intent.putExtra("email", trip.getPassngerEmail());
                               startActivity(intent);
                               }

                           @Override
                           public void onFailure(Exception exception) {
                               Toast.makeText(getBaseContext(), "הוספת הנסיעה נכשלה", Toast.LENGTH_LONG).show();
                           }

                           @Override
                           public void onProgress(String status, double percent) {
                               if( percent != 100) {
                                   Toast.makeText(getBaseContext(),"upload trip data",Toast.LENGTH_SHORT).show();
                                   mLoadMeButton.setEnabled(false);
                               }
                           }
                       });
                   }
               });
               try {
                   addTripAsynsTask.execute(trip);
                   } catch (Exception e){
                    Log.d("Abu", e.getMessage());
                    Toast.makeText(getBaseContext(), "Error \n", Toast.LENGTH_LONG).show();
                    mLoadMeButton.setEnabled(true);
                    }
            }
            });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mLocation = getPlace(location);
                tripOrig.setHint(mLocation);
                mLoadMeButton.setEnabled(true);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        }

    protected Trip createTripFromFields() {
        Trip trip = new Trip();

        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS,0);
        trip.setPassengerName( prefs.getString(DISPLAY_NAME_KEY,"username"));
      //  trip.setPassengerName(mFullName.getText().toString());
        trip.setPassengerPhone(prefs.getString(DISPLAY_PHONE,"phone"));
      //  trip.setPassngerEmail(mEmail.getText().toString());
        trip.setPickUpLoc(mOrigin);
        trip.setDestinationLoc(mDestination);
        //trip.setTripStartTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        trip.setTripStatus(mTrip.Available);
        return trip;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fineViews();
        getLocation();
    }



//gps get location metod

    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);


        } else {
            // Android version is less than 6.0 or the permission is already granted.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }
    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                String adress = addresses.get(0).getAddressLine(0);
                return adress;
            }

            return "no place: \n ("+location.getLongitude()+" , "+location.getLatitude()+")";
        }
        catch(
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
