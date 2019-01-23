package com.HaHa.abuyoyo.model.datasource;

import android.support.annotation.NonNull;

import com.HaHa.abuyoyo.model.backend.Backend;
import com.HaHa.abuyoyo.model.backend.BackendFactory;
import com.HaHa.abuyoyo.model.entities.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_DBManager implements Backend {

    static DatabaseReference tripsRef;

    public String getID() {
        return key;
    }



    static String key;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tripsRef = database.getReference("Trips");
    }



    @Override
    public void addRequest(Trip trip ,final Action<Void> action) {

        key = tripsRef.push().getKey();

        trip.setId(key);

        Task<Void> task = tripsRef.child(key).setValue(trip);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(aVoid);
                action.onProgress("upload trip data", 100);

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload student data", 100);
            }
        });


    }

    @Override
    public void deleteTrip(String key)
    {
        tripsRef.child(key).removeValue();
    }
}


