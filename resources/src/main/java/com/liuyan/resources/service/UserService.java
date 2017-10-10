package com.liuyan.resources.service;

import com.liuyan.resources.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyan on 2017/10/10.
 */
@Service
public class UserService {
    public List<UserVo> getUsers() {
        List<UserVo> userVos = new ArrayList<>();
        UserVo userVo = new UserVo();
        userVo.setAge(12);
        userVo.setBirthday(new Date());
        userVo.setName("yan");
        userVos.add(userVo);
        return userVos;
    }
}
