package com.yaozhou.springboot_shiro.service;

import com.yaozhou.springboot_shiro.pojo.Users;

/**
 * Created by WXHang on HANG at 2021/9/9 17:33
 * Desc：
 */
public interface UserService {
    Users queryUserByName(String username);
}
