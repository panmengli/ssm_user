package com.hy.hy.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hy.hy.TrueUtils;
import com.hy.hy.mapper.DeptMapper;
import com.hy.hy.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Service
public class DeptServices extends ServiceImpl<DeptMapper, Dept> implements IDept{
    @Autowired
    private DeptMapper deptMapper;
    public List<Dept> getdept(){
        return deptMapper.selectByExample(null);
    }

    public List<TrueUtils> selectTrue(){
        List<TrueUtils> list=new ArrayList<>();
        List<Dept> deptList=deptMapper.selectList(null);
        return list;
    }

}
