package com.hy.hy.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hy.hy.LayuiUtil;
import com.hy.hy.UtisMsg;
import com.hy.hy.pojo.Dept;
import com.hy.hy.pojo.Emp;
import com.hy.hy.pojo.Post;
import com.hy.hy.services.IDept;
import com.hy.hy.services.IEmp;
import com.hy.hy.services.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Controller
@RequestMapping("/empController")
public class EmpController {

    @Autowired
    private IEmp iEmp;

    @Autowired
    private IDept iDept;

    @Autowired
    private IPost iPost;

    /**
     * 查询所有员工信息
     *
     * @param
     * @return
     */
    @RequestMapping("/queryAllEmp.do")
    @ResponseBody//响应
    public LayuiUtil queryAllEmp(Emp emp, Integer page, Integer limit) {
        LayuiUtil layuiUtil = new LayuiUtil();
        EntityWrapper empEntityWrapper = new EntityWrapper<Emp>();
        if (!StringUtils.isEmpty(emp)) {
            if (!StringUtils.isEmpty(emp.getName())) {
                empEntityWrapper.eq("name", emp.getName());
            }
            if (!StringUtils.isEmpty(emp.getSex())) {
                empEntityWrapper.eq("sex", emp.getSex());
            }
        }
        Page<Emp> pages = new Page<>(page, limit);
        Page<Emp> ipage = iEmp.selectPage(pages, empEntityWrapper);
        System.out.println(pages);
        layuiUtil.setCode(0);
        layuiUtil.setMsg("查询成功");
        layuiUtil.setCount((int) ipage.getTotal());
        layuiUtil.setData(ipage.getRecords());
        return layuiUtil;
    }

    /**
     * 上传员工图片
     *
     * @param filename
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/pictureUpload.do")
    @ResponseBody//响应
    public UtisMsg pictureUpload(@RequestParam("file") MultipartFile filename, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置图片名称 不能重复
        String picName = UUID.randomUUID().toString();
        //获取文件名
        String oriName = filename.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        //获取到的是当前项目的绝对路径
        String filePath = request.getServletContext().getRealPath("/");
        File file = new File(filePath);
        //开始上传
        filePath = file.getParentFile().toString();

        File parentfile = new File(filePath + "\\upload\\" + picName + extName);
        System.out.println(parentfile);
        filename.transferTo(parentfile);
        UtisMsg utisMsg = new UtisMsg();
        utisMsg.setCode(0);
        utisMsg.setMsg("");
        utisMsg.setData("\\upload\\" + picName + extName);
        return utisMsg;
    }

    @RequestMapping("/deleteAll.do")
    @ResponseBody
    public String deleteAll(String str) {
        if (!StringUtils.isEmpty(str)) {
            String[] ids = str.split(",");
            for (String id : ids) {
                if(!StringUtils.isEmpty(id)){
                    iEmp.deleteById(Integer.parseInt(id));
                }
            }
            return "success";
        }else {
            return null;
        }
    }


    /**
     * 添加一条员工信息
     *
     * @param emp
     */
    @RequestMapping("/savaEmp.do")
    public void savaEmp(Emp emp) {
        iEmp.insert(emp);
    }

    /**
     * 根据id查询一条
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateEmp.do")
    public String updateEmp(Integer id, Model model) {
        System.out.println("修改");
        model.addAttribute("emp", iEmp.selectById(id));
        model.addAttribute("deptList", iDept.selectList(null));
        model.addAttribute("postList", iPost.selectList(null));
        return "/LayuiEmp/updateEmp.html";
    }

    /**
     * 修改员工信息
     *
     * @param emp
     */
    @RequestMapping("/updateEmpToId.do")
    public void updateEmpToId(Emp emp) {
        iEmp.updateById(emp);
    }

    /**
     * 根据id删除员工
     *
     * @param id
     */
    @RequestMapping("/deleteEmpById.do")
    @ResponseBody
    public String deleteEmpById(Integer id) {
        System.out.println("删除员工");
        iEmp.deleteById(id);
        return "success";
    }

    /**
     * 添加一条员工信息
     *
     * @param emp
     * @param filename
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/addemp.do")
    public void addemp(Emp emp, @RequestParam("filename") MultipartFile filename, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置图片名称 不能重复
        String picName = UUID.randomUUID().toString();
        //获取文件名
        String oriName = filename.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        //获取到的是当前项目的绝对路径
        String filePath = request.getServletContext().getRealPath("/");
        File file = new File(filePath);
        //开始上传
        filePath = file.getParentFile().toString();

        File parentfile = new File(filePath + "\\upload\\" + picName + extName);
        System.out.println(parentfile);
        filename.transferTo(parentfile);
        emp.setPicture("\\upload\\" + picName + extName);
        iEmp.insert(emp);
        response.sendRedirect("/empController/queryAllEmp.do");
    }

    /**
     * 删除一条员工信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteemp.do")
    public String deleteemp(Integer id) {
        iEmp.deleteById(id);
        return "redirect:/empController/queryAllEmp.do";
    }

    /**
     * 根据id查询一条员工信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryEmpById.do")
    public String queryEmpById(Integer id, Model model) {
        Emp emp = iEmp.selectById(id);
        List<Dept> deptList = iDept.selectList(null);
        List<Post> postList = iPost.selectList(null);
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("emp", emp);
        return "/updateEmp";
    }

    /**
     * 修改一条员工信息
     *
     * @param emp
     * @param filename
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/updateEmpTo.do")
    public void updateemp(Emp emp, @RequestParam("filename") MultipartFile filename, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置图片名称 不能重复
        String picName = UUID.randomUUID().toString();
        //获取文件名
        String oriName = filename.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String filePath = new File(request.getServletContext().getRealPath("/")).getParent() + "\\upload\\" + picName + extName;
        //开始上传
        filename.transferTo(new File(filePath));
        emp.setPicture("\\upload\\" + picName + extName);
        iEmp.updateById(emp);
        response.sendRedirect("/empController/queryAllEmp.do");
    }

}
