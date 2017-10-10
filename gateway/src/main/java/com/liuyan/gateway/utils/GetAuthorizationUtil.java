package com.liuyan.gateway.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.List;

/**
 * Created by liuyan on 2017/10/10.
 */
public class GetAuthorizationUtil {
    @Autowired
    TokenStore tokenStore;

    public List<String> getAuthorization(String jwt) {
        //todo jwt合法性校验、decodejwt 获取有效Authorization
        return null;
    }
}
