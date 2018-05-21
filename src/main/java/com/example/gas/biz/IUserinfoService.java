package com.example.gas.biz;

import com.example.gas.entity.Userinfo;

import java.util.List;

/**
 * @author li
 * @create 2018-05-11 16:08
 * @desc
 **/
public interface IUserinfoService {
    //    用户查询 分页
    List<Userinfo> findByPage(int pageNo, int pageSize);
   //   用户查询 不分页
   List<Userinfo> findByNoPage();
    // 通过用户名查询
    List<Userinfo> searchByName(int i, int i1, String unit_name);

    int setTop(String id, String set_top);
}
