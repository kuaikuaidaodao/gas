package com.example.gas.biz.iml;

import com.example.gas.biz.IUserinfoService;
import com.example.gas.Mapper.UserinfoMapper;
import com.example.gas.entity.Userinfo;
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
public class UserinfoService implements IUserinfoService{
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public List<Userinfo> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userinfoMapper.getList();
    }

    @Override
    public List<Userinfo> searchByName(int pageNo, int pageSize, String unit_name) {
        PageHelper.startPage(pageNo, pageSize);
        return userinfoMapper.searchByName(unit_name);
    }
}
