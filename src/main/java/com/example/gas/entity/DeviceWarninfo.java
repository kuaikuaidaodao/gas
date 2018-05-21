package com.example.gas.entity;

public class DeviceWarninfo {
    private long device_id;
    private String type;
    private Warninfo warninfo;

    public long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Warninfo getWarninfo() {
        return warninfo;
    }

    public void setWarninfo(Warninfo warninfo) {
        this.warninfo = warninfo;
    }
}
