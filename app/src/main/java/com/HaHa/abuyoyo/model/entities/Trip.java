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
    private String idDriver;
    private String cityDestination;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String  tripDistance;

    public String getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(String tripDistance) {
        this.tripDistance = tripDistance;
    }


    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

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
