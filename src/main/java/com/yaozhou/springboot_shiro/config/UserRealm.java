package com.yaozhou.springboot_shiro.config;

import com.yaozhou.springboot_shiro.pojo.Users;
import com.yaozhou.springboot_shiro.service.ServiceImp.UserServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by WXHang on HANG at 2021/9/9 15:32
 * Desc：
 */



//自定义的Realm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImp userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        Users user = (Users) subject.getPrincipal();
        String perms = user.getPerms();
        System.out.println(perms);
        //这里可以在数据库获取角色权限
        info.addStringPermission(perms);
        System.out.println("进行了授权");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //认证
        System.out.println("进行了认证");
        Users sysUser = null;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从数据库对用户名和密码进行认证
        sysUser = userService.queryUserByName(token.getUsername());

        if (sysUser == null){
            return null;
        }


        return new SimpleAuthenticationInfo(sysUser,sysUser.getPwd(),"") {
        };
    }
}
