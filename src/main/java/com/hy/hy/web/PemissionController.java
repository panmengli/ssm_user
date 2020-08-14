package com.hy.hy.web;

import com.hy.hy.pojo.Permission;
import com.hy.hy.services.IPemission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
@Controller
@RequestMapping("/roleController")
public class PemissionController {
    @Autowired
    private IPemission iPemission;


    @RequestMapping("/addPermission.do")
    public void  addPermission(Permission permission){
        iPemission.insert(permission);
    }

}
