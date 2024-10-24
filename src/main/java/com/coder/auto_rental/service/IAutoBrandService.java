package com.coder.auto_rental.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.auto_rental.entity.AutoBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface IAutoBrandService extends IService<AutoBrand> {
    Page<AutoBrand> searchByPage(Page<AutoBrand> page,AutoBrand autoBrand);
}
