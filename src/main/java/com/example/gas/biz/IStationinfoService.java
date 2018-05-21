package com.example.gas.biz;

import com.example.gas.entity.Stationinfo;

import java.util.List;

public interface IStationinfoService {
    int insert(Stationinfo stationinfo);

    //站点信息查询
    List<Stationinfo> getList(int pageNo, int pageSize);

    //通过单位名称查询站点信息
    List<Stationinfo> serchByUnitName(int pageNo, int pageSize, String unit_name);

    // 站点修改
    int update(String device_id, String name);

    List<Stationinfo> selectStation(int pageNo, int pageSize, int user_id);

    int insertStationAndUser(String user_id, String station_id);
}
