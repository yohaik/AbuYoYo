package com.HaHa.abuyoyo.model.entities;

public class Trip {
    private enum mTrip{
        Available,
        InProgress,
        Completed
    };
    private double pickUpLoc;
    private double destinationLoc;
    private String tripStartTime;
    private String tripEndTime;
    private String passengerName;
    private String passengerPhone;
    private String passngerEmail;

    public double getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(double pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
    }

    public double getDestinationLoc() {
        return destinationLoc;
    }

    public void setDestinationLoc(double destinationLoc) {
        this.destinationLoc = destinationLoc;
    }

    public String getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public String getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(String tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPassngerEmail() {
        return passngerEmail;
    }

    public void setPassngerEmail(String passngerEmail) {
        this.passngerEmail = passngerEmail;
    }
}
