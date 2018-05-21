package com.example.gas.Controller;


import com.example.gas.Config.Common;
import com.example.gas.Mapper.StationinfoMapper;
import com.example.gas.Mapper.UserinfoMapper;
import com.example.gas.biz.IUserinfoService;
import com.example.gas.entity.Stationinfo;
import com.example.gas.entity.Userinfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author li
 * @create 2018-05-11 13:23
 * @desc 用户信息
 **/
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
    @Autowired
    UserinfoMapper userinfoMapper;
    @Autowired
    IUserinfoService iUserinfoService;
    @Autowired
    StationinfoMapper stationinfoMapper;

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("login")
    Map login(String userName, String password) {
        Userinfo userinfo = userinfoMapper.login(userName, password);
        Map map = new HashMap();
        if (userinfo != null) {
            map.put("status", "success");
            map.put("msg", userinfo);
        } else {
            map.put("status", "error");
            map.put("msg", "用户名不存在或密码错误");

        }
        return map;
    }

    /**
     * 通过id查询信息
     *
     * @param id
     * @return
     */
    @RequestMapping("getById")
    Userinfo getById(int id) {
        return userinfoMapper.get(id);
    }

    /**
     * 用户分页
     * @param pageNo
     * @return
     */
    @RequestMapping("getList")
    PageInfo<Userinfo> getList(int pageNo) {
        List<Userinfo> userinfos = iUserinfoService.findByPage(pageNo, Common.USERPAGESIZE);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Userinfo> pageInfo = new PageInfo<Userinfo>(userinfos);
        return pageInfo;
    }

    /**
     * 用户不分页
     * @return
     */
    @RequestMapping("getListNoPage")
    List<Userinfo> getListNoPage() {
        List<Userinfo> userinfos = iUserinfoService.findByNoPage();
        return userinfos;
    }
    @RequestMapping("searchByName")
    PageInfo<Userinfo> searchByName(String unit_name, int pageNo) {
        List<Userinfo> userinfos = iUserinfoService.searchByName(pageNo, Common.USERPAGESIZE, unit_name);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Userinfo> pageInfo = new PageInfo<Userinfo>(userinfos);
        return pageInfo;
    }

    /**
     * 增加
     */
    @RequestMapping("insert")
    int insert(Userinfo userinfo) {
        return userinfoMapper.insert(userinfo);
    }

    /**
     * 修改
     */
    @RequestMapping("update")
    int update(Userinfo userinfo) {
        return userinfoMapper.update(userinfo);
    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    int delete(int index) {
        return userinfoMapper.delete(index);
    }
    /**
     **通过userid查询user下站点
     */
    @RequestMapping("selectStation")
    List<Stationinfo> selectStation(int user_id) {
        return stationinfoMapper.selectStation(user_id);
    }
    /**
     * 待分配站点
     */
    @RequestMapping("selectStationNo")
    List<Stationinfo> selectStationNo(int user_id) {
        return stationinfoMapper.selectStationNo(user_id);
    }
    /**
     * 取消分配
     */
    @RequestMapping("deleteStationAndUser")
    int deleteStationAndUser(String user_id,String station_id){
        return stationinfoMapper.deleteStationAndUser(user_id,station_id);
    }
}
