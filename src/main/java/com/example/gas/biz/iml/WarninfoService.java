package com.example.gas.biz.iml;

import com.example.gas.Mapper.WarninfoMapper;
import com.example.gas.biz.IWarninfoService;
import com.example.gas.entity.DeviceWarninfo;
import com.example.gas.entity.Warninfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author li
 * @create 2018-05-11 16:07
 * @desc
 **/
@Service
@Transactional(readOnly = true)
public class WarninfoService implements IWarninfoService {
    @Autowired
    private WarninfoMapper warninfoMapper;


    @Override
    public List<DeviceWarninfo> getList(String deviceId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return warninfoMapper.getList(deviceId);
    }
}
