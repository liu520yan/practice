package com.liuyan.resources.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuyan.resources.service.UserService;
import com.liuyan.resources.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by liuyan on 2017/10/10.
 */
@Slf4j
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

    @RequestMapping(method = RequestMethod.GET, path = "/on")
    public JSONObject isLife(HttpServletRequest httpServletRequest) {
        Enumeration<String> stringEnumeration = httpServletRequest.getHeaderNames();
        while (stringEnumeration.hasMoreElements()) {
            String name = stringEnumeration.nextElement();
            log.info("hearder：  " + name + ":" + httpServletRequest.getHeader(name));
            System.out.println();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "on");
        return jsonObject;
    }
}
