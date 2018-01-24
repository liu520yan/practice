package com.liuyan.gateway.fileter;

import com.liuyan.gateway.utils.IpUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liuyan on 2018/1/24.
 */
@Slf4j
@Component
public class IpFilter extends ZuulFilter {

    @Autowired
    RedisTemplate redisTemplate;

    @Override

    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURI().equals("login")) {
            log.info("进入登录页面，不校验ip。ip:{}", IpUtils.getIpAddr(request));
            return null;
        }
        if (redisTemplate.opsForValue().get(request.getRemoteAddr()) != null) {
            log.info("ip:{}已经登录过，无需再次登录！", request.getRemoteAddr());
            return null;
        } else {
            log.info("ip:{}没有登录，需要登录！", request.getRemoteAddr());
        }

        try {
            ctx.getResponse().sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
