package com.liuyan.resources.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyan on 2017/10/11.
 */
@RestController
@RequestMapping("/test")
public class TestsController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public JSONObject get() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        return jsonObject;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public JSONObject post() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        return jsonObject;
    }
}
