package com.liuyan.resources.controller;

import com.liuyan.resources.service.UserService;
import com.liuyan.resources.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by liuyan on 2017/10/10.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('READ')")
    @RequestMapping(method = RequestMethod.GET, path = "/user")
    public List<UserVo> getUsers() {
        return userService.getUsers();
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @RequestMapping(method = RequestMethod.POST, path = "/user")
    public String writeUser() {
        return "write user " + UUID.randomUUID().toString();
    }
}
