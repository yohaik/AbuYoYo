package com.HaHa.abuyoyo.model.backend;

import android.content.ContentValues;
import android.content.Context;

import com.HaHa.abuyoyo.model.entities.Trip;

public interface Backend {
    void addRequest(ContentValues trip, final Context context);
}
