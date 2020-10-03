package com.meet.mogo;

import com.google.firebase.firestore.PropertyName;

public class Model_Join_forth {


    private String name,email,mobileno,payment,received,userid;

    public Model_Join_forth() {

    }

    public Model_Join_forth(String name, String email, String mobileno, String payment, String received, String userid) {
        this.name = name;
        this.email = email;
        this.mobileno = mobileno;
        this.payment = payment;
        this.received = received;
        this.userid = userid;
    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
