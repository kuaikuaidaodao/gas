package com.example.gas.Controller;


import com.example.gas.biz.IMenuService;
import com.example.gas.entity.Menu;
import com.example.gas.entity.Menuinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    IMenuService iMenuService;
    @RequestMapping("getList")
    List<Menuinfo> getList(){
        List<Menuinfo> menuinfos=new ArrayList<>();
        List<Menu> list=iMenuService.getList();
        for (Menu menu:list){
            Menuinfo menuinfo=new Menuinfo();
            menuinfo.setId(menu.getId());
            menuinfo.setMenuName(menu.getName());
            menuinfo.setList(iMenuService.getListByParentId(menu.getId()));
            menuinfos.add(menuinfo);
        }
        return menuinfos;
    }
}
