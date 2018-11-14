package com.HaHa.abuyoyo.model.entities;

public class User {
    private String firstName;
    private String lastName;
    private String userId;
    private String phoneNum;
    private String emailAddress;
    private String creditCard;

    //set

    void setFirstName(String firstName) {this.firstName= firstName;}

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public void setEmailAddress(String emailAdress) { this.emailAddress = emailAdress; }

    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }


    //get

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getUserId() { return userId; }

    public String getPhoneNum() { return phoneNum; }

    public String getEmailAddress() { return emailAddress; }

    public String getCreditCard() { return creditCard; }


}
