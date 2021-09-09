package com.yaozhou.springboot_shiro.service.ServiceImp;

import com.yaozhou.springboot_shiro.mapper.UserMapper;
import com.yaozhou.springboot_shiro.pojo.Users;
import com.yaozhou.springboot_shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WXHang on HANG at 2021/9/9 17:33
 * Desc：
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Users queryUserByName(String username) {
        Users user = userMapper.queryUserByName(username);
        return user;
    }
}
