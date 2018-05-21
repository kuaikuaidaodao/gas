package com.example.gas.Mapper;

import com.example.gas.entity.DeviceWarninfo;
import com.example.gas.entity.Userinfo;
import com.example.gas.entity.Warninfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface WarninfoMapper {
    int insert(@Param("pojo") Warninfo pojo);

    int insertList(@Param("pojo") List<Warninfo> pojos);

    int update(@Param("pojo") Warninfo pojo);

    int saveOrUpdate(@Param("pojo") Warninfo pojo);

    List<DeviceWarninfo> getList();

    DeviceWarninfo getById(@Param("device_id") String device_id);
}
