package com.coder.auto_rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.auto_rental.entity.Dept;
import com.coder.auto_rental.mapper.DeptMapper;
import com.coder.auto_rental.service.IDeptService;
import com.coder.auto_rental.utils.DeptTreeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public List<Dept> selectList(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(dept.getDeptName()), "dept_name", dept.getDeptName());
        queryWrapper.orderByAsc("order_num");
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        return DeptTreeUtils.buildTree(depts, 0);

    }

    @Override
    public List<Dept> selectTree() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        Dept dept = new Dept();
        dept.setDeptName("所有部门").setId(0).setPid(-1);
        depts.add(dept);
        return DeptTreeUtils.buildTree(depts, -1);

    }

    @Override
    public boolean haschildren(Integer id) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", id);
        return baseMapper.selectCount(queryWrapper) > 0;
    }
}
