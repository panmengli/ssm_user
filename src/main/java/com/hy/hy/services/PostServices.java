package com.hy.hy.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hy.hy.mapper.PostMapper;
import com.hy.hy.pojo.Post;
import org.springframework.stereotype.Service;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Service
public class PostServices extends ServiceImpl<PostMapper, Post> implements IPost{
}
