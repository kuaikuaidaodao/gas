package com.example.gas;


import com.example.gas.biz.IUserinfoService;
import com.example.gas.entity.Userinfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Controller
@SpringBootApplication
public class GasApplication {
    @Autowired
    IUserinfoService iUserinfoService;

    @RequestMapping("/")
    @ResponseBody
    PageInfo<Userinfo> home() {
        List<Userinfo> persons = iUserinfoService.findByPage(1, 2);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Userinfo> pageInfo = new PageInfo<Userinfo>(persons);
        return pageInfo;
    }

    public static void main(String[] args) {
        SpringApplication.run(GasApplication.class, args);
    }

    @Bean
    PageHelper pageHelper() {
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
