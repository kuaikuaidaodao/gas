package com.example.gas.biz;

import com.example.gas.entity.DeviceDateCurrent;
import com.example.gas.entity.DeviceDateHistory;
import com.example.gas.entity.Deviceinfo;

import java.util.List;

public interface IDeviceDateCurrentService {
//    设备信息实时查询（通过单位名称）
    List<DeviceDateCurrent> getList(int pageNo, int pageSize,int index);
//    设备信息实时查询 全部
    List<DeviceDateCurrent> getListDefault(int pageNo, int pageSize);
// 设备历史信息查询（通过device_id)
    List<DeviceDateHistory> getListHistory(int pageNo, int pagesize, String device_id);
// 设备信息查询
    List<Deviceinfo> getDeviceList(int pageNo, int pageSize);

    List<Deviceinfo> searchDeviceList(int pageNo, int pageSize,String container_id);

    List<DeviceDateCurrent> searchBydevice_idOrstation(int pageNo, int devicepagesize, String searchinfo);
}
