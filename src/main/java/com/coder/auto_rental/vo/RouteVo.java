package com.coder.auto_rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteVo {
    private String path;
    private String component;
    private String name;
    private Boolean alwaysShow;
    private List<RouteVo> children;
    private Meta meta;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public   class Meta {
        private String title;
        private String icon;
        private Object[] roles;

    }
}
