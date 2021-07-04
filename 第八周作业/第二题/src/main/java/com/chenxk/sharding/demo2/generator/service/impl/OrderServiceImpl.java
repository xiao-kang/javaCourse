package com.chenxk.sharding.demo2.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenxk.sharding.demo2.generator.domain.Order;
import com.chenxk.sharding.demo2.generator.service.OrderService;
import com.chenxk.sharding.demo2.generator.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
implements OrderService{

}




