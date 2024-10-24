package com.coder.auto_rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.auto_rental.entity.AutoMaker;
import com.coder.auto_rental.mapper.AutoMakerMapper;
import com.coder.auto_rental.service.IAutoMakerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@Service
public class AutoMakerServiceImpl extends ServiceImpl<AutoMakerMapper, AutoMaker> implements IAutoMakerService {

    @Override
    public Page<AutoMaker> search(int start, int size, AutoMaker autoMaker) {
        QueryWrapper<AutoMaker> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_letter")
                .like(StrUtil.isNotEmpty(autoMaker.getName()),"name",autoMaker.getName());
        Page<AutoMaker> page = new Page<>(start,size);
        this.page(page, queryWrapper);
        return page;
    }
}
