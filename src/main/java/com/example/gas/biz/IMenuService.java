package com.example.gas.biz;

import com.example.gas.entity.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getList();
    List<Menu> getListByParentId(int parentId);
}
