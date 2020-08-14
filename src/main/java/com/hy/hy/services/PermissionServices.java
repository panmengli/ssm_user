package com.hy.hy.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hy.hy.mapper.PermissionMapper;
import com.hy.hy.pojo.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
@Service
@Transactional
public class PermissionServices extends ServiceImpl<PermissionMapper, Permission> implements IPemission {

}
