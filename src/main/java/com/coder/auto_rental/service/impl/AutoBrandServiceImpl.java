package com.coder.auto_rental.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coder.auto_rental.entity.AutoBrand;
import com.coder.auto_rental.mapper.AutoBrandMapper;
import com.coder.auto_rental.service.IAutoBrandService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
@Service
public class AutoBrandServiceImpl extends ServiceImpl<AutoBrandMapper, AutoBrand> implements IAutoBrandService {
    @Resource
    private AutoBrandMapper autoBrandMapper;

    @Override
    public Page<AutoBrand> searchByPage(Page<AutoBrand> page,
                                        AutoBrand autoBrand) {
        return autoBrandMapper.searchByPage(page,autoBrand);
    }
}
