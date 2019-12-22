package com.nikati.manage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * 
 *@Title ManageServletInitializer.java
 *@description Guns Web程序启动类
 *@time 2019年12月22日 下午8:52:53
 *@author Nikati
 *@version 1.0
*
 */
public class ManageServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebstackGunsApplication.class);
    }
}
