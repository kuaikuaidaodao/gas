package com.example.gas.Mapper;

import com.example.gas.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author li
 * @create 2018-05-11 10:59
 * @desc
 **/
@Mapper
@Service
public interface UserinfoMapper {
    int insert(@Param("pojo") Userinfo pojo);


    int update(@Param("pojo") Userinfo pojo);

    Userinfo get(@Param("id") int id);

    List<Userinfo> getList();

    int delete(@Param("index") int index);

    Userinfo login(@Param("userName") String userName, @Param("password") String password);

    List<Userinfo> searchByName(@Param("unit_name") String unit_name);

    int setTop(@Param("id")String id, @Param("set_top")String set_top);
}
