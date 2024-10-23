package com.coder.auto_rental.service.impl;

import com.coder.auto_rental.entity.Permission;
import com.coder.auto_rental.mapper.PermissionMapper;
import com.coder.auto_rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> selectPermissionListByUserId(int userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }
}
