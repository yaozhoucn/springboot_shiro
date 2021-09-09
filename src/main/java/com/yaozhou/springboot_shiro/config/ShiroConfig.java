package com.yaozhou.springboot_shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by WXHang on HANG at 2021/9/9 15:29
 * Desc：
 */
@Configuration
public class ShiroConfig {
    //shiroFilterFactoryBean，subject：第三步创建
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro过滤器
        /**
         * anno:无需认证就可以访问
         * authc:必须认证才能访问
         * user：必须拥有记住我功能，才能访问
         * perms：必须拥有某个资源的权限才能访问
         * role: 拥有某个角色才能访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/add","authc");
        filterChainDefinitionMap.put("/update","authc");
        filterChainDefinitionMap.put("/login","anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //未认证则进入登陆页面
        factoryBean.setLoginUrl("/login");

        return factoryBean;
    }


    //DafultSecuritymanage：第二创建
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm，对realm进行管理
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }


    //创建realm对象；需要组定义类 ：第一步
    //没自定义名称，方法名即为名称
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
