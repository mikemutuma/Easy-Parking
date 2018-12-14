package com.mutuma.easyparking;

public class Item {
    String reg,spot,hour;

    public Item(String reg, String spot, String hour) {
        this.reg = reg;
        this.spot = spot;
        this.hour = hour;
    }

    public Item() {
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
