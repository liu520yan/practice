package com.liuyan.gateway.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by liuyan on 2018/1/24.
 */
@Data
public class User implements Serializable {
    String password;
    String username;
}
