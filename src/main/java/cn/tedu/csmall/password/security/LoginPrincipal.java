package cn.tedu.csmall.password.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginPrincipal implements Serializable {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
}
