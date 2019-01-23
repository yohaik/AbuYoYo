package com.HaHa.abuyoyo.model.backend;

import com.HaHa.abuyoyo.model.entities.Trip;

public interface Backend {

    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    void addRequest(Trip trip,final Action<Void> action);

    String getID();
     void deleteTrip(String key);

}
