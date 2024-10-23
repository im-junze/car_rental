package com.coder.auto_rental.vo;

import lombok.Data;

@Data
public class TokenVo {
    private String token;
    private Long expireTime;
}
