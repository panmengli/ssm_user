package com.hy.hy.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.hy.hy.LayuiUtil;
import com.hy.hy.pojo.Role;
import com.hy.hy.services.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
@Controller
@RequestMapping("/roleController")
public class RoleController {
    @Autowired
    private IRole iRole;

    @RequestMapping("/queryRole.do")
    @ResponseBody
    public LayuiUtil queryRole(Integer page,Integer limit){
        LayuiUtil layuiUtil = new LayuiUtil();
        Page<Role> pages = new Page<>(page, limit);
        Page<Role> ipage = iRole.selectPage(pages);
        System.out.println(pages);
        layuiUtil.setCode(0);
        layuiUtil.setMsg("查询成功");
        layuiUtil.setCount((int) ipage.getTotal());
        layuiUtil.setData(ipage.getRecords());
        return layuiUtil;
    }

    /**
     * 添加
     * @param role
     */
    @RequestMapping("/addRole.do")
    public void  addRole(Role role){
        iRole.insert(role);
    }

    @RequestMapping("/deleteRole.do")
    public void deleteRole(Integer id){
        iRole.deleteById(id);
    }

}
