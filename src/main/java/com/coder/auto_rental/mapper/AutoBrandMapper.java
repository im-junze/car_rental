package com.coder.auto_rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.auto_rental.entity.AutoBrand;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface AutoBrandMapper extends BaseMapper<AutoBrand> {
    Page<AutoBrand> searchByPage(@Param("page") Page<AutoBrand> page, @Param("autoBrand") AutoBrand autoBrand);
}
