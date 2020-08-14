package com.hy.hy.web;

import com.hy.hy.pojo.Dept;
import com.hy.hy.services.IDept;
import com.hy.hy.services.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Controller
@RequestMapping("/deptController")
public class DeptController {
    @Autowired
    private IDept iDept;
    @Autowired
    private IPost iPost;

    @PostMapping("/getdept.do")
    @ResponseBody//响应json请求
    public List<Dept> getdept(Model model){
        return  iDept.selectList(null);
    }




}
