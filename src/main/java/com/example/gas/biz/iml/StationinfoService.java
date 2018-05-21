package com.example.gas.biz.iml;

import com.example.gas.Mapper.StationinfoMapper;
import com.example.gas.biz.IStationinfoService;
import com.example.gas.entity.Stationinfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StationinfoService implements IStationinfoService {
    @Autowired
    StationinfoMapper stationinfoMapper;
    @Override
    public int insert(Stationinfo stationinfo) {
        return   stationinfoMapper.insert(stationinfo);
    }


    @Override
    public List<Stationinfo> getList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return stationinfoMapper.getList();
    }

    @Override
    public List<Stationinfo> serchByUnitName(int pageNo, int pageSize, String unit_name) {
        PageHelper.startPage(pageNo, pageSize);
        return stationinfoMapper.serchByUnitName(unit_name);
    }

    @Override
    public int update(String device_id, String name) {
    return stationinfoMapper.update(device_id,name);
    }
}
