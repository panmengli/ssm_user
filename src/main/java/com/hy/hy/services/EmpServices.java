package com.hy.hy.services;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hy.hy.mapper.EmpMapper;
import com.hy.hy.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/7/22
 */
@Service
@Transactional
public class EmpServices extends ServiceImpl<EmpMapper, Emp> implements IEmp {

    /**
     * 查询所有
     */
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getStuAll(Emp emp) {
        System.out.println("====="+emp);
        if(null!=emp){
            return empMapper.getstuLike(emp);
        }else{
            return empMapper.queryAllEmp();
        }
    }

    /**
     * 添加一条员工信息
     * @param emp
     * @return
     *//*
    public int addemp(Emp emp){
        return empMapper.insertSelective(emp);
    }

    *//**
     * 删除一条员工信息
     * @param id
     * @return
     *//*
    public int deleteemp(Integer id){
        return empMapper.deleteByPrimaryKey(id);
    }

    *//**
     * 根据id查询一条员工信息
     * @param id
     * @return
     *//*
    public Emp queryEmpById(Integer id){
        return empMapper.selectByPrimaryKey(id);
    }

    *//**
     * 修改员工信息
     * @param emp
     * @return
     *//*
    public int updateemp(Emp emp){
        return empMapper.updateByPrimaryKey(emp);
    }*/
}
