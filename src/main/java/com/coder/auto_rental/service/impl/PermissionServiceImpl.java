package com.coder.auto_rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coder.auto_rental.entity.Permission;
import com.coder.auto_rental.mapper.PermissionMapper;
import com.coder.auto_rental.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.auto_rental.utils.RouteTreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> selectPermissionListByUserId(int userId) {
        return baseMapper.selectPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> selectList( ) {
        QueryWrapper <Permission> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions = baseMapper.selectList(queryWrapper);
        return RouteTreeUtils.buildMenuTree(permissions,0);
    }

    @Override
    public List<Permission> selectTree() {
        QueryWrapper <Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("permission_type",2);
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissions= baseMapper.selectList(queryWrapper);
        Permission permission = new Permission();
        permission.setId(0).setPid(-1).setPermissionLabel("根目录");
        permissions.add(permission);
        return RouteTreeUtils.buildMenuTree(permissions,-1);
    }

    @Override
    public boolean hasChildren(Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        return baseMapper.selectCount(queryWrapper)>0;
    }
}
