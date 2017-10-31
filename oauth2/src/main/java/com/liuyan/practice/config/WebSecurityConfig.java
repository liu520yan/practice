package com.liuyan.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

import static org.springframework.security.jwt.codec.Codecs.b64UrlEncode;
import static org.springframework.security.jwt.codec.Codecs.concat;

/**
 * Created by liuyan on 2017/10/9.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                //.formLogin().and() // 基于Form表单的认证，用户可自定义
                .httpBasic(); // 启用HTTPBasic认证
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(); 可指定从数据库加载用户信息
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .authorities("READ")
                .and()
                .withUser("admin")
                .password("admin")
                .authorities("READ", "WRITE");
    }

    public static void main(String[] args) {

//        String verifierKey = new RandomValueStringGenerator().generate();
//
//        Signer signer = new MacSigner(verifierKey);
//
//        byte[] crypto = signer
//                .sign(concat(b64UrlEncode(header.bytes()), PERIOD, b64UrlEncode(claims)));
    }
}
