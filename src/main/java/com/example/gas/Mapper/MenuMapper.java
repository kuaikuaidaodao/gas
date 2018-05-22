package com.example.gas.Mapper;

import com.example.gas.entity.Menu;
import com.example.gas.entity.UserMenu;
import com.example.gas.entity.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMenu userMenu);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getList();

    List<Menu> getListByParentId(int parentId);
}