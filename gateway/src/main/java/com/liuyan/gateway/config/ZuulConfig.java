package com.liuyan.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyan.gateway.fileter.AuthorityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by liuyan on 2017/10/10.
 */
@Configuration
public class ZuulConfig {
//    @Bean
//    public AuthorityFilter getAuthorityFilter() {
//        return new AuthorityFilter();
//    }

    @Bean
    public ObjectMapper newObject() {
        return new ObjectMapper();
    }

}
