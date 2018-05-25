package com.example.gas.biz.iml;

import com.example.gas.Mapper.DeviceinfoMapper;
import com.example.gas.biz.IDeviceDateCurrentService;
import com.example.gas.entity.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceDateCurrentService implements IDeviceDateCurrentService {
    @Autowired
    DeviceinfoMapper deviceinfoMapper;

    @Override
    public List<DeviceDateCurrent> getList(int pageNo, int pageSize, int index) {
        PageHelper.startPage(pageNo, pageSize);
        return deviceinfoMapper.getList(index);
    }

    @Override
    public List<DeviceDateCurrent> getListDefault(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return deviceinfoMapper.getListDefault();
    }

    @Override
    public List<DeviceDateHistory> getListHistory(int pageNo, int pagesize, String device_id) {
        PageHelper.startPage(pageNo, pagesize);
        return deviceinfoMapper.getListHistory(device_id);
    }

    @Override
    public List<Deviceinfo> getDeviceList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return deviceinfoMapper.getDeviceList();
    }

    @Override
    public List<Deviceinfo> searchDeviceList(int pageNo, int pageSize, String container_id) {
        PageHelper.startPage(pageNo, pageSize);
        return deviceinfoMapper.searchDeviceList(container_id);
    }

    @Override
    public List<DeviceDateCurrent> searchBydevice_idOrstation(int pageNo, int pageSize, String info) {
        PageHelper.startPage(pageNo, pageSize);
        return deviceinfoMapper.searchBydevice_idOrstation(info);
    }

    @Override
    public List<DaviceInfoCurrent> getListDefaultNo() {
        return deviceinfoMapper.getListDefaultNo();
    }

    @Override
    public List<WarnListinfo> getListWarn() {
        return deviceinfoMapper.getListWarn();
    }

    @Override
    public List<DeviceDateHistory> getListHistoryByDate(int pageNo, int pagesize, String device_id, String startTime, String endTime) {
        PageHelper.startPage(pageNo, pagesize);
        return deviceinfoMapper.getListHistoryByDate(device_id,startTime,endTime);
    }
}
