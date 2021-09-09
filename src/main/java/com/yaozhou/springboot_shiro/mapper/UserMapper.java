package com.yaozhou.springboot_shiro.mapper;

import com.yaozhou.springboot_shiro.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by WXHang on HANG at 2021/9/9 17:32
 * Desc：
 */
@Repository
@Mapper
public interface UserMapper {
    Users queryUserByName(String username);
}
