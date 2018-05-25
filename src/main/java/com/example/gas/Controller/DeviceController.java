package com.example.gas.Controller;

import com.example.gas.Config.Common;
import com.example.gas.Mapper.DeviceinfoMapper;
import com.example.gas.biz.IDeviceDateCurrentService;
import com.example.gas.entity.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceinfoMapper deviceinfoMapper;
    @Autowired
    IDeviceDateCurrentService iDeviceDateCurrentService;

    /**
     * 查询目前设备实时数据（根据单位名称查询）
     *
     * @param
     * @param pageNo
     * @return
     */
    @RequestMapping("getList")
    public PageInfo<DeviceDateCurrent> getList(int index, int pageNo) {
        List<DeviceDateCurrent> deviceDateCurrents = iDeviceDateCurrentService.getList(pageNo, Common.DEVICEPAGESIZE, index);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<DeviceDateCurrent> pageInfo = new PageInfo<DeviceDateCurrent>(deviceDateCurrents);
        return pageInfo;
    }

    /**
     * 查询目前设备所有实时数据
     *
     * @param pageNo
     * @return
     */
    @RequestMapping("getListDefault")
    public PageInfo<DeviceDateCurrent> getListDefault(int pageNo) {
        List<DeviceDateCurrent> deviceDateCurrents = iDeviceDateCurrentService.getListDefault(pageNo, Common.DEVICEPAGESIZE);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<DeviceDateCurrent> pageInfo = new PageInfo<DeviceDateCurrent>(deviceDateCurrents);
        return pageInfo;

    }
    /**
     * 查询目前设备所有实时数据中报警项
     *
     * @param
     * @return
     */
    @RequestMapping("getListWarn")
    public List getListWarn() {
        List<WarnListinfo> deviceDateCurrents = iDeviceDateCurrentService.getListWarn();
        boolean warn=false;
        Map map=null;
        Map map3=null;
        Map map2=null;
        List list=null;
        List list2=new ArrayList();
        for (WarnListinfo warnListinfo:deviceDateCurrents){
            map=new HashMap();
            map3=new HashMap();
            map2=new HashMap();
            list=new ArrayList();
            warn=false;
            if (warnListinfo.getHeight_temperature()!=null){
                if (warnListinfo.getDaviceInfoCurrent().getTemperature_liquid()>Float.parseFloat(warnListinfo.getHeight_temperature())){
                    warn=true;
                    map.put("name","温度");
                    map.put("value",warnListinfo.getDaviceInfoCurrent().getTemperature_liquid());
                    list.add(map);
                }
            }
            if (warnListinfo.getLow_temperature()!=null){
                if (warnListinfo.getDaviceInfoCurrent().getTemperature_liquid()<Float.parseFloat(warnListinfo.getLow_temperature())){
                    warn=true;
                    map.put("name","温度");
                    map.put("value",warnListinfo.getDaviceInfoCurrent().getTemperature_liquid());
                    list.add(map);
                }
            }
            if (warnListinfo.getHeight_temperature()!=null){
                if (Integer.valueOf(warnListinfo.getDaviceInfoCurrent().getPressure_top())>Integer.valueOf(warnListinfo.getHeight_pressure())){
                    warn=true;
                    map3.put("name","压力");
                    map3.put("value",warnListinfo.getDaviceInfoCurrent().getPressure_top());
                    list.add(map3);
                }
            }
            if (warnListinfo.getLow_temperature()!=null){
                if (Integer.valueOf(warnListinfo.getDaviceInfoCurrent().getPressure_top())<Integer.valueOf(warnListinfo.getLow_pressure())){
                    warn=true;
                    map3.put("name","压力");
                    map3.put("value",warnListinfo.getDaviceInfoCurrent().getPressure_top());
                    list.add(map3);
                }
            }
            if (warn){
                map2.put("device_id",warnListinfo.getDevice_id());
                map2.put("msg",list);
                list2.add(map2);
            }
        }
        return list2;

    }
    /**
     * 查询目前设备所有实时数据不分页（站点地图）
     *
     * @param
     * @return
     */
    @RequestMapping("getListDefaultNo")
    public List<DaviceInfoCurrent> getListDefaultNo() {
        List<DaviceInfoCurrent> deviceDateCurrents = iDeviceDateCurrentService.getListDefaultNo();
        return deviceDateCurrents;
    }
    @RequestMapping("searchBydevice_idOrstation")
    public PageInfo<DeviceDateCurrent> searchBydevice_idOrstation(int pageNo, String info) {
        List<DeviceDateCurrent> deviceDateCurrents = iDeviceDateCurrentService.searchBydevice_idOrstation(pageNo, Common.DEVICEPAGESIZE, info);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<DeviceDateCurrent> pageInfo = new PageInfo<DeviceDateCurrent>(deviceDateCurrents);
        return pageInfo;
    }

    /**
     * 根据设备id查询设备详情
     *
     * @param device_id
     * @return
     */
    @RequestMapping("getDetail")
    public DeviceDateCurrent getDetail(String device_id) {
        DeviceDateCurrent deviceDateCurrent = deviceinfoMapper.getDetail(device_id);
        return deviceDateCurrent;
    }

    /**
     * 根据设备id查询设备历史数据
     *
     * @param device_id
     * @param pageNo
     * @return
     */
    @RequestMapping("getListHistory")
    public Map getListHistory(String device_id, int pageNo) {
        List<DeviceDateHistory> deviceDateHistory = iDeviceDateCurrentService.getListHistory(pageNo, Common.DEVICEPAGESIZE, device_id);
        Map map = new HashMap();
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<DeviceDateHistory> pageInfo = new PageInfo<DeviceDateHistory>(deviceDateHistory);
        map.put("deviceinfo", deviceinfoMapper.getDeviceListByDervice_id(device_id));
        map.put("deviceHistoryinfo", pageInfo);
        return map;
    }
    /**
     * 根据时间段查询设备历史数据
     *
     * @param
     * @param pageNo
     * @return
     */
    @RequestMapping("getListHistoryByDate")
    public Map getListHistoryByDate(String device_id,Date startTime,Date endTime, int pageNo) {
        SimpleDateFormat sdf=new SimpleDateFormat("yy/MM/dd,HH:mm:ss");
        String startTimeFormart=sdf.format(startTime);
        String endTimeFormart=sdf.format(endTime);
        List<DeviceDateHistory> deviceDateHistory = iDeviceDateCurrentService.getListHistoryByDate(pageNo, Common.DEVICEPAGESIZE,device_id,startTimeFormart,endTimeFormart);
        Map map = new HashMap();
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<DeviceDateHistory> pageInfo = new PageInfo<DeviceDateHistory>(deviceDateHistory);
        map.put("deviceinfo", deviceinfoMapper.getDeviceListByDervice_id(device_id));
        map.put("deviceHistoryinfo", pageInfo);
        return map;
    }
    /**
     * 查询设备信息（设备管理模块）
     *
     * @param pageNo
     * @return
     */
    @RequestMapping("getDeviceList")
    public PageInfo<Deviceinfo> getDeviceList(int pageNo) {
        List<Deviceinfo> deviceinfos = iDeviceDateCurrentService.getDeviceList(pageNo, Common.DEVICEPAGESIZE);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Deviceinfo> pageInfo = new PageInfo<Deviceinfo>(deviceinfos);
        return pageInfo;
    }

    /**
     * 根据罐箱id查询设备
     *
     * @param pageNo
     * @param container_id
     * @return
     */
    @RequestMapping("searchDeviceList")
    public PageInfo<Deviceinfo> searchDeviceList(int pageNo, String container_id) {
        List<Deviceinfo> deviceinfos = iDeviceDateCurrentService.searchDeviceList(pageNo, Common.DEVICEPAGESIZE, container_id);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInf0
        PageInfo<Deviceinfo> pageInfo = new PageInfo<Deviceinfo>(deviceinfos);
        return pageInfo;
    }

    /**
     * 修改设备信息
     *
     * @param deviceinfo
     * @return
     */
    @RequestMapping("update")
    public int update(Deviceinfo deviceinfo) {
        return deviceinfoMapper.update(deviceinfo);
    }
}
