package com.example.gas.entity;

/**
 * @author li
 * @create 2018-05-11 10:54
 * @desc 用户
 **/
public class Stationinfo {
    private int index;
    private String name;
    private String unit_name;
    private String longitude;
    private String latitude;
    private long device_id;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_Name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }
}







