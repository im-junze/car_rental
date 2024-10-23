package com.coder.auto_rental.controller;

import com.coder.auto_rental.entity.User;
import com.coder.auto_rental.service.IUserService;
import com.coder.auto_rental.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/auto_rental/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("test")
    public String list(String username){
        return "ok";
    }
}
