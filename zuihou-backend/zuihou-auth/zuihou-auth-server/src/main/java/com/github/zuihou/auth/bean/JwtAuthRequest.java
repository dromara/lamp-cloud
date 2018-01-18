package com.github.zuihou.auth.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest implements Serializable {
    private String userName;
    private String passWord;

    //public JwtAuthRequest() {
    //}

    //public JwtAuthRequest(String userName, String passWord) {
    //    this.userName = userName;
    //    this.passWord = passWord;
    //}
}
