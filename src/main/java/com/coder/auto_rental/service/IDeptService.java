package com.coder.auto_rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coder.auto_rental.entity.Dept;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zdd
 * @since 2024-10-21
 */
public interface IDeptService extends IService<Dept> {
    List<Dept> selectList(Dept dept);
    List<Dept> selectTree();

    boolean haschildren(Integer id);
}
