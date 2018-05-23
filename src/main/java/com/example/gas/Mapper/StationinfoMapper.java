package com.example.gas.Mapper;

import com.example.gas.entity.Stationinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface StationinfoMapper {
    int insert(Stationinfo stationinfo);

    int update(@Param("pojo") Stationinfo pojo);

    List<Stationinfo> getList();

    List<Stationinfo> serchByUserId(@Param("index") String index);

    List<Stationinfo> serchByUnitName(@Param("unit_name") String unit_name);
    List<Stationinfo> getStationList();

    List<Map> getListByName(@Param("name") String name);

    List<Stationinfo> getGroupListByNameOrImit(@Param("info") String info);

    int update(@Param("device_id") String device_id, @Param("name") String name);

    List<Stationinfo> selectStation(@Param("user_id") int user_id);

    List<Stationinfo> selectStationNo(@Param("user_id") int user_id);

    int deleteStationAndUser(@Param("user_id") String user_id, @Param("station_id") String station_id);

    int insertStationAndUser(@Param("user_id") String user_id, @Param("station_id") String station_id);
}
