package com.hy.hy.web;

import com.hy.hy.pojo.Post;
import com.hy.hy.services.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Controller
@RequestMapping("/postController")
public class PostController {
    @Autowired
    private IPost iPost;

    @PostMapping("/getpost.do")
    @ResponseBody//响应json请求
    public List<Post> getpost(){
        return  iPost.selectList(null);
    }




}
