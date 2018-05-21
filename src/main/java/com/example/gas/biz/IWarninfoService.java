package com.example.gas.biz;

import com.example.gas.entity.DeviceWarninfo;
import com.example.gas.entity.Userinfo;
import com.example.gas.entity.Warninfo;

import java.util.List;

/**
 * @author li
 * @create 2018-05-11 16:08
 * @desc
 **/
public interface IWarninfoService {

    //设备及警告信息
    List<DeviceWarninfo> getList(int pageNo, int pagesize);
}
