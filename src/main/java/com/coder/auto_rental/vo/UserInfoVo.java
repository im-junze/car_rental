package com.coder.auto_rental.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoVo implements Serializable {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private Object[] roles;
}
