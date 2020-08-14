package com.hy.hy.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hy.hy.mapper.RoleMapper;
import com.hy.hy.pojo.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
@Service
@Transactional
public class RoleServices extends ServiceImpl<RoleMapper, Role> implements IRole {

}
