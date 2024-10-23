package com.coder.auto_rental.mapper;

import com.coder.auto_rental.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> selectRoleNameByUserId(int id);
}
