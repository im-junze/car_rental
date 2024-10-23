package com.coder.auto_rental.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public   class Meta {
        private String title;
        private String icon;
        private Object[] roles;

    }
}
