package com.hy.hy.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.hy.hy.LayuiUtil;
import com.hy.hy.pojo.User;
import com.hy.hy.services.IUser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private IUser userServices;

    Logger logger = LogManager.getLogger(UserController.class.getName());

    @RequestMapping("/queryUser.do")
    public String queryUser(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getId().toString(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user1 = userServices.selectById(user.getId());
        subject.getSession().setAttribute("user", user1);
        return "redirect:/LayuiEmp/fremset.html";
    }

    /**
     * 查询所有用户
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectUseryAll.do")
    @ResponseBody
    public LayuiUtil queryRole(Integer page, Integer limit){
        LayuiUtil layuiUtil = new LayuiUtil();
        Page<User> pages = new Page<>(page, limit);
        Page<User> ipage = userServices.selectPage(pages);
        System.out.println(pages);
        layuiUtil.setCode(0);
        layuiUtil.setMsg("查询成功");
        layuiUtil.setCount((int) ipage.getTotal());
        layuiUtil.setData(ipage.getRecords());
        return layuiUtil;
    }



    /**
     * 添加一个用户
     * @param user
     */
    @RequestMapping("/addUser.do")
    public void addUser(User user){
        try {
            userServices.insert(user);
            logger.info("添加用户成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加时异常",e);
        }
    }

    /**
     * 根据id查询一整条
     * @param id
     * @return
     */
    @RequestMapping("/selectUserById.do")
    public User selectUserById(Integer id){
      return  userServices.selectById(id);
    }

    /**
     * 删除一个用户
     * @param id
     */
    @RequestMapping("/deleteUserById.do")
    public void deleteUserById(Integer id){
        userServices.deleteById(id);
    }

}
