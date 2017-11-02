package com.liuyan.gateway.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import java.util.List;

/**
 * Created by liuyan on 2017/10/10.
 */
public class GetAuthorizationUtil {

    public List<String> getAuthorization(String jwt) {
        //todo jwt合法性校验、decodejwt 获取有效Authorization
        return null;
    }

//    public static void main(String[] args) {
//        Jwt jwt = JwtHelper.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.mMMWrQZZAiVWAp5ekR7h8sRpB3NjPM8e2UPof0qiohZi6Mdf4V1xWs79Pu63bmcdXdZV2binlIaW0Qs410pjIjVKrmnO0NHwPch7AvMYgBZ64b2dW-ViTKNyHAGW_faV-W7bfDzsV8b7oxnq9YKUt8EpfXWIQ-x58U-2pQT2NzHjO6e9igu2vBTt2kUpYhusO0vAwYFLkVqkit7WurpoExlUPUmk9icQzde2nfTEGMz1hlB5HVnYGgKkdKDQaDO4tUvJrz3Mkl2WpVTqGb7Aa_s3RvWHCKYKTiHUuGQSkWHVgggddvTUXAiKtwI-EcaUqiNVtGOlwc5Y8pCnIA3RIQ");
//        System.out.println(jwt);
//
//    }

}
