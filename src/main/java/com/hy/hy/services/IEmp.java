package com.hy.hy.services;

import com.baomidou.mybatisplus.service.IService;
import com.hy.hy.pojo.Emp;

import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/7/24
 */
public interface IEmp extends IService<Emp> {
    List<Emp> getStuAll(Emp emp);
}
