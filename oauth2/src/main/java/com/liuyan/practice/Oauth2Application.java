package com.liuyan.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by liuyan on 2017/10/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
