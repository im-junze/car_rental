package com.coder.auto_rental.service;

import com.coder.auto_rental.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission>selectPermissionListByUserId(int userId);
}
