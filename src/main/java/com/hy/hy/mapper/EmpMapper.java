package com.hy.hy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hy.hy.pojo.Emp;
import com.hy.hy.pojo.EmpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {
    int countByExample(EmpExample example);

    int deleteByExample(EmpExample example);

    int deleteByPrimaryKey(Integer id);

    //int insert(Emp record);

    int insertSelective(Emp record);

    List<Emp> selectByExample(EmpExample example);

    Emp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);

    /**
     *      * 联合查询所有员工信息
     *      * @return
     */
    public List<Emp> queryAllEmp();

    public List<Emp> getstuLike(Emp emp);
}