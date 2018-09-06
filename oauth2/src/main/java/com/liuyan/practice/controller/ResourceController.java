package com.liuyan.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by liuyan on 2017/10/9.
 */
@RestController
public class ResourceController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
