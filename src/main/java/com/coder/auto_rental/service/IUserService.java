package com.coder.auto_rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.auto_rental.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface IUserService extends IService<User> {
    public User selectByUsername(String username);
    List<String> selectRoleName(int id);
}
