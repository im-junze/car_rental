package com.coder.auto_rental.service.impl;

import com.coder.auto_rental.entity.Customer;
import com.coder.auto_rental.mapper.CustomerMapper;
import com.coder.auto_rental.service.ICustomerService;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
