package com.upc.bean;

public class User {

    private String number;
    private String password;
    private String plan;
    private boolean hasSubscrition;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public boolean isHasSubscrition() {
        return hasSubscrition;
    }

    public void setHasSubscrition(boolean hasSubscrition) {
        this.hasSubscrition = hasSubscrition;
    }
}
