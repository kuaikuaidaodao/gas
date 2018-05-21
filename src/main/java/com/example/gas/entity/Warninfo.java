package com.example.gas.entity;

public class Warninfo {
    private Integer low_weight;
    private Integer height_weight;
    private Integer low_pressure;
    private Integer height_pressure;
    private String low_temperature;
    private String height_temperature;
    private String low_voltage;
    private String height_voltage;
    private String device_id;

    public Warninfo() {
    }

    public Integer getLow_weight() {
        return low_weight;
    }

    public void setLow_weight(Integer low_weight) {
        this.low_weight = low_weight;
    }

    public Integer getHeight_weight() {
        return height_weight;
    }

    public void setHeight_weight(Integer height_weight) {
        this.height_weight = height_weight;
    }

    public Integer getLow_pressure() {
        return low_pressure;
    }

    public void setLow_pressure(Integer low_pressure) {
        this.low_pressure = low_pressure;
    }

    public Integer getHeight_pressure() {
        return height_pressure;
    }

    public void setHeight_pressure(Integer height_pressure) {
        this.height_pressure = height_pressure;
    }

    public String getLow_temperature() {
        return low_temperature;
    }

    public void setLow_temperature(String low_temperature) {
        this.low_temperature = low_temperature;
    }

    public String getHeight_temperature() {
        return height_temperature;
    }

    public void setHeight_temperature(String height_temperature) {
        this.height_temperature = height_temperature;
    }

    public String getLow_voltage() {
        return low_voltage;
    }

    public void setLow_voltage(String low_voltage) {
        this.low_voltage = low_voltage;
    }

    public String getHeight_voltage() {
        return height_voltage;
    }

    public void setHeight_voltage(String height_voltage) {
        this.height_voltage = height_voltage;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    @Override
    public String toString() {
        return "Warninfo{" +
                "low_weight=" + low_weight +
                ", height_weight=" + height_weight +
                ", low_pressure=" + low_pressure +
                ", height_pressure=" + height_pressure +
                ", low_temperature=" + low_temperature +
                ", height_temperature=" + height_temperature +
                ", low_voltage=" + low_voltage +
                ", height_voltage=" + height_voltage +
                ", device_id='" + device_id + '\'' +
                '}';
    }
}
