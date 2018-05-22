package com.example.gas.Controller;

import com.example.gas.Config.Common;
import com.example.gas.Mapper.DeviceinfoMapper;
import com.example.gas.biz.IDeviceDateCurrentService;
import com.example.gas.entity.DeviceDateCurrent;
import com.example.gas.entity.DeviceDateHistory;
import com.example.gas.entity.Deviceinfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param index
     * @param pageNo
     * @return
     */
    @RequestMapping("getList")
    public PageInfo<DeviceDateCurrent> getList(String index, int pageNo) {
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
     * 查询目前设备所有实时数据不分页（站点地图）
     *
     * @param
     * @return
     */
    @RequestMapping("getListDefaultNo")
    public List<DeviceDateCurrent> getListDefaultNo() {
        List<DeviceDateCurrent> deviceDateCurrents = iDeviceDateCurrentService.getListDefaultNo();
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
