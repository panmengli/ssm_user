package com.hy.hy.realm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hy.hy.pojo.User;
import com.hy.hy.services.IUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 潘梦丽
 * @create 2020/8/4
 */
public class RealmDemo extends AuthorizingRealm {
    @Autowired
    private IUser userServices;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //将authenticationToken转成UsernamePasswordToken获取用户名
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
        System.out.println("====="+usernamePasswordToken.getUsername());
        //根据用户名查询数据库
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.eq("id",usernamePasswordToken.getUsername());
        User user=(User) userServices.selectOne(entityWrapper);
        if(null==user){
            throw new UnknownAccountException("此用户不存在!");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}
