package com.liuyan.gateway.comtroller;

import com.liuyan.gateway.user.User;
import com.liuyan.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyan on 2018/1/24.
 */
@Slf4j
@Controller
public class Login {

    @Value("${login.userName}")
    String userName;
    @Value("${login.password}")
    String password;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/index")
    public String index() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request) {
        if (this.userName.equals(user.getUsername())
                && this.password.equals(user.getPassword())) {

            log.info("登录成功！ ip : {}", getIpAddr(request));
            redisTemplate.opsForValue().set(getIpAddr(request), getIpAddr(request));
            return "functionList";
        } else {
            log.info("登录失败！ ip : {}", getIpAddr(request));
            return null;
        }
    }

    private String getIpAddr(HttpServletRequest request) {
        return IpUtils.getIpAddr(request);
    }

    private long flushRedis() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return ldt.toEpochSecond(ZoneOffset.of("+8")) - System.currentTimeMillis();
    }
}
