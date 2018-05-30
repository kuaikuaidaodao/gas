package com.example.gas.Controller;

import com.example.gas.Config.Common;
import com.example.gas.Mapper.DeviceinfoMapper;
import com.example.gas.biz.IDeviceDateCurrentService;
import com.example.gas.entity.*;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
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
     * @param
     * @return
     */
    @RequestMapping("getListHistoryByDate")
    public Map getListHistoryByDate(String device_id,String startTime,String endTime) {
        List<DeviceDateHistory> deviceDateHistory = iDeviceDateCurrentService.getListHistoryByDate(device_id,startTime,endTime);
        Map map = new HashMap();
        map.put("deviceinfo", deviceinfoMapper.getDeviceListByDervice_id(device_id));
        map.put("deviceHistoryinfo", deviceDateHistory);
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
    /**
     * 360消费贷报表导出
     *
     * @param
     * @param response
     */
    @RequestMapping("/device_export")
    public void LoanExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String device_id=request.getParameter("device_id");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        // 创建一个webbook，对应一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        // 在webbook中添加一个sheet，对应excel表中的sheet
        HSSFSheet sheet = hssfWorkbook.createSheet();
        // 在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID号");
        row.createCell(1).setCellValue("IMEI");
        row.createCell(2).setCellValue("设备名称");
        row.createCell(3).setCellValue("站点名称");
        row.createCell(4).setCellValue("介质重量(kg)");
        row.createCell(5).setCellValue("质量(%)");
        row.createCell(6).setCellValue("压力(MPa)");
        row.createCell(7).setCellValue("温度(℃)");
        row.createCell(8).setCellValue("生产商");
        row.createCell(9).setCellValue("容器编号");
        row.createCell(10).setCellValue("上报时间间隔(min)");
        row.createCell(11).setCellValue("电池电压");
        row.createCell(12).setCellValue("公称容积");
        row.createCell(13).setCellValue("容器类型");
        row.createCell(14).setCellValue("介质类型");
        row.createCell(15).setCellValue("数据时间");
        row.createCell(16).setCellValue("制造日期 ");
        Map map=this.getListHistoryByDate(device_id,startTime,endTime);
        List<DeviceDateHistory> deviceDateHistories=null;
        Deviceinfo deviceinfo=null;
        if (map!=null){
          deviceDateHistories= (List<DeviceDateHistory>) map.get("deviceHistoryinfo");
          deviceinfo= (Deviceinfo) map.get("deviceinfo");
        }
        // 写入实体数据(维度查询和二级过滤以后的数据)
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        for (int i = 0; i < deviceDateHistories.size(); i++) {
            row = sheet.createRow(i + 1);
            DeviceDateHistory excelEntity = deviceDateHistories.get(i);
                row.createCell(0).setCellValue(device_id);
                row.createCell(1).setCellValue(device_id);
            if (null != deviceinfo.getType()&&!"".equals(deviceinfo.getType())) {
                row.createCell(2).setCellValue(deviceinfo.getType());
            }
            if (null != deviceinfo.getStation_name()&&!"".equals(deviceinfo.getStation_name())) {
                row.createCell(3).setCellValue(deviceinfo.getStation_name());
            }
//            介质重量
            if (null != excelEntity.getWeight()&&!"".equals(excelEntity.getWeight())) {
                row.createCell(4).setCellValue(excelEntity.getWeight());
            }
            /**
             * 质量百分比 暂略 5
             */
//            压力
            if (null != excelEntity.getPressure_top()&&!"".equals( excelEntity.getPressure_top())) {
                row.createCell(6).setCellValue(excelEntity.getPressure_top());
            }
//            温度
            if (null!= excelEntity.getTemperature_gas()&&!"".equals(excelEntity.getTemperature_gas())) {
                row.createCell(7).setCellValue(excelEntity.getTemperature_gas());
            }
//            生产商
            if (null != deviceinfo.getManufacturer()&&!"".equals(deviceinfo.getManufacturer())) {
                row.createCell(8).setCellValue(deviceinfo.getManufacturer());
            }
//            容器编号
            if (null != deviceinfo.getContainer_num()&&!"".equals(deviceinfo.getContainer_num())) {
                row.createCell(9).setCellValue(deviceinfo.getContainer_num());
            }
//            时间间隔
            if (!"".equals(excelEntity.getData_interval())) {
                row.createCell(10).setCellValue(excelEntity.getData_interval());
            }
            /**
             * 电池电压 11
             */
//            公称容积
            if (null != deviceinfo.getVolume()&&!"".equals(deviceinfo.getVolume())) {
                row.createCell(12).setCellValue(deviceinfo.getVolume());
            }
//            容器类型
            if (null != deviceinfo.getContainer_type()&&!"".equals(deviceinfo.getContainer_type())) {
                row.createCell(13).setCellValue(deviceinfo.getContainer_type());
            }
//            介质类型
            if (null != deviceinfo.getMedium()&&!"".equals(deviceinfo.getMedium())) {
                row.createCell(14).setCellValue(deviceinfo.getMedium());
            }
//            数据时间
            if (null != excelEntity.getData_time()&&!"".equals(excelEntity.getData_time())) {
                row.createCell(15).setCellValue(excelEntity.getData_time());
            }
//            制造日期
            if (null != deviceinfo.getCheck_time()&&!"".equals(deviceinfo.getCheck_time())) {
                row.createCell(16).setCellValue(deviceinfo.getCheck_time());
            }
        }
        // 浏览器端下载excel(可以另存到指定的目录，目前测试支持IE、谷歌，其他浏览器没有测试)
        OutputStream out = null;
        Date now=new Date();
        try {
            out = response.getOutputStream();
            String fileName =sdf.format(now)+".xls";// 文件名
            response.setContentType("application/x-msdownload");// 告诉浏览器返回数据的格式
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));// 告诉浏览器返回的数据打开的方法
            hssfWorkbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
