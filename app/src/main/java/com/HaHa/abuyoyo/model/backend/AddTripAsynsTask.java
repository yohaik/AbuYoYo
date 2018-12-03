package com.HaHa.abuyoyo.model.backend;

import android.os.AsyncTask;

import com.HaHa.abuyoyo.model.entities.Trip;

public class AddTripAsynsTask extends AsyncTask<Trip,Void,Void> {

    public interface  AddTripAsynsTaskFunction
    {   public void onPreExecute();
        public void onPostExecute();
        public void doInBackground(Trip trip);
    }
    AddTripAsynsTaskFunction addTripAsynsTaskFunction;

    public AddTripAsynsTask(AddTripAsynsTaskFunction addTripAsynsTaskFunction)
    {         this.addTripAsynsTaskFunction = addTripAsynsTaskFunction;
    }


    @Override
    protected Void doInBackground(Trip... trips) {
        addTripAsynsTaskFunction.doInBackground(trips[0]);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        addTripAsynsTaskFunction.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        addTripAsynsTaskFunction.onPostExecute();
    }
}
