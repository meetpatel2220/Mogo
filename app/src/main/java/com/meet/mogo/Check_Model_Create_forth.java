package com.meet.mogo;

public class Check_Model_Create_forth {

    private String name,price,deadline,details,deadlineend;

    public Check_Model_Create_forth(String name, String price, String deadline, String details, String deadlineend) {
        this.name = name;
        this.price = price;
        this.deadline = deadline;
        this.details = details;
        this.deadlineend = deadlineend;

    }


    public Check_Model_Create_forth() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDeadlineend() {
        return deadlineend;
    }

    public void setDeadlineend(String deadlineend) {
        this.deadlineend = deadlineend;
    }


}
