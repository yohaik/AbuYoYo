package com.HaHa.abuyoyo.model.entities;

public class Trip {

    private String pickUpLoc;
    private String destinationLoc;
    private String tripStartTime;
    private String tripEndTime;
    private String passengerName;
    private String passengerPhone;
    private String passngerEmail;
    private mTrip tripStatus;

    public mTrip getTripStatus() { return tripStatus; }

    public void setTripStatus(mTrip tripStatus) { this.tripStatus = tripStatus; }

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
    }

    public String getDestinationLoc() {
        return destinationLoc;
    }

    public void setDestinationLoc(String destinationLoc) {
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
