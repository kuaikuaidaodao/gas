package com.example.gas.biz.iml;

import com.example.gas.Mapper.MenuMapper;
import com.example.gas.biz.IMenuService;
import com.example.gas.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuService implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> getList() {
        return menuMapper.getList();
    }

    @Override
    public List<Menu> getListByParentId(int parentId) {
        return menuMapper.getListByParentId(parentId);
    }
}
