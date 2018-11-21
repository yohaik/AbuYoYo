package com.HaHa.abuyoyo.model.datasource;

import android.support.annotation.NonNull;

import com.HaHa.abuyoyo.model.backend.Backend;
import com.HaHa.abuyoyo.model.backend.BackendFactory;
import com.HaHa.abuyoyo.model.entities.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Firebase_DBManager implements Backend {

    static DatabaseReference tripsRef;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tripsRef = database.getReference("Trips");
    }

    @Override
    public void addRequest(Trip trip ,final Action<Void> action) {
        Task<Void> task = tripsRef.push().setValue(trip);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(aVoid);
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
            }
        });

    }
}


