package com.xw.robot.model;

import com.xw.model.CityEnum;

public class MyLocation {
    CityEnum myCity;
    int x;
    int y;

    public CityEnum getMyCity() {
        return myCity;
    }

    public void setMyCity(CityEnum myCity) {
        this.myCity = myCity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
