package com.coder.auto_rental.service.impl;

import com.coder.auto_rental.entity.Order;
import com.coder.auto_rental.mapper.OrderMapper;
import com.coder.auto_rental.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
