package com.example.gas.Controller;

import com.example.gas.Config.Common;
import com.example.gas.Mapper.DeviceinfoMapper;
import com.example.gas.Mapper.StationinfoMapper;
import com.example.gas.biz.IStationinfoService;
import com.example.gas.entity.StationGroup;
import com.example.gas.entity.Stationinfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author li
 * @create 2018-05-11 13:33
 * @desc 站点信息
 **/
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    IStationinfoService iStationinfoService;
    @Autowired
    StationinfoMapper stationinfoMapper;
    @Autowired
    DeviceinfoMapper deviceinfoMapper;

    /**
     * 站点增加
     *
     * @param stationinfo
     * @return
     */
    @RequestMapping("insert")
    int insert(Stationinfo stationinfo) {
        Map map = deviceinfoMapper.getItude(stationinfo.getDevice_id());
        if (map != null) {
            stationinfo.setLatitude(map.get("latitude").toString());
            stationinfo.setLongitude(map.get("longitude").toString());
        }
        return iStationinfoService.insert(stationinfo);

    }

    /**
     * 站点修改
     *
     * @param
     * @return
     */
    @RequestMapping("update")
    int update(String device_id, String name) {
        return iStationinfoService.update(device_id, name);

    }

    /**
     * 站点列表
     *
     * @param pageNo
     * @return
     */
    @RequestMapping("getList")
    PageInfo<Stationinfo> getList(int pageNo) {
        List<Stationinfo> stationinfos = iStationinfoService.getList(pageNo, Common.STATIONPAGESIZE);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Stationinfo> pageInfo = new PageInfo<Stationinfo>(stationinfos);
        return pageInfo;
    }

    /**
     * 查询
     *
     * @param pageNo
     * @param unit_name
     * @return
     */
    @RequestMapping("getGroupList")
    PageInfo<StationGroup> getGroupList(int pageNo, String unit_name) {
        List<Stationinfo> stationinfos = null;
        if (unit_name != null && unit_name != "") {
            stationinfos = stationinfoMapper.serchByUnitName(unit_name);
        } else {
            stationinfos = stationinfoMapper.getStationList();
        }
        List<StationGroup> stationGroups = new ArrayList<>();
        StationGroup stationGroup;
        for (Stationinfo station : stationinfos) {
            stationGroup = new StationGroup();
            stationGroup.setName(station.getName());
            stationGroup.setList(stationinfoMapper.getListByName(station.getName()));
            stationGroups.add(stationGroup);
        }
        PageHelper.startPage(pageNo, Common.DEVICEPAGESIZE);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<StationGroup> pageInfo = new PageInfo<StationGroup>(stationGroups);
        return pageInfo;
    }
    /**
     * 备用
     *
     * @param pageNo
     * @param unit_name
     * @return
     */
//    @RequestMapping("getGroupListBEI")
//    PageInfo<StationGroup> getGroupListBEI(int pageNo, String unit_name) {
//        List<Stationinfo> stationinfos = null;
//        if (unit_name != null && unit_name != "") {
//            stationinfos = stationinfoMapper.serchByUnitName(unit_name);
//        } else {
//            stationinfos = stationinfoMapper.getStationList();
//        }
//        List<StationGroup> stationGroups = new ArrayList<>();
//        StationGroup stationGroup;
//        for (Stationinfo station : stationinfos) {
//            stationGroup = new StationGroup();
//            stationGroup.setName(station.getName());
//            stationGroup.setList(stationinfoMapper.getListByName(station.getName()));
//            stationGroups.add(stationGroup);
//        }
//        PageHelper.startPage(pageNo, Common.DEVICEPAGESIZE);
//        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
//        PageInfo<StationGroup> pageInfo = new PageInfo<StationGroup>(stationGroups);
//        return pageInfo;
//    }

    /**
     * IMIT 站点名称
     *
     * @param
     * @param info
     * @return
     */
    @RequestMapping("getGroupListByNameOrImit")
    StationGroup getGroupListByNameOrImit(String info) {
        List<Stationinfo> list = stationinfoMapper.getGroupListByNameOrImit(info);
        StationGroup stationGroup = new StationGroup();
        List list2 = null;
        if (list.size() > 0) {
            stationGroup.setName(list.get(0).getName());
            stationGroup.setDevice_id(list.get(0).getDevice_id());
            list2 = new ArrayList();
            for (Stationinfo stationinfo : list) {
                list2.add(stationinfoMapper.getListByName(stationinfo.getName()));
            }
        }
        stationGroup.setList(list2);
        return stationGroup;
    }

    /**
     * 通过所属单位名称查询站点
     *
     * @param unit_name
     * @param pageNo
     * @return
     */
    @RequestMapping("serchByUnitName")
    public PageInfo<Stationinfo> serchByUnitName(String unit_name, int pageNo) {
        List<Stationinfo> stationinfos = iStationinfoService.serchByUnitName(pageNo, Common.STATIONPAGESIZE, unit_name);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Stationinfo> pageInfo = new PageInfo<Stationinfo>(stationinfos);
        return pageInfo;
    }
}
