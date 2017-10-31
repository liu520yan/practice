package com.liuyan.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by liuyan on 2017/10/10.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ResourcesApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                ResourcesApplication.class)
                .web(true).run(args);
    }
}
