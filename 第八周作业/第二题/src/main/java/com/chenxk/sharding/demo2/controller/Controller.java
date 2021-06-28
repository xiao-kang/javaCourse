package com.chenxk.sharding.demo2.controller;


import com.chenxk.sharding.demo2.generator.domain.Order;
import com.chenxk.sharding.demo2.generator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author chenxiaokang
 * @date 2021/6/27
 */
@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    private OrderService orderService;


    @GetMapping("/save")
    public String testSave(){
        for (int i = 0 ; i< 100 ; i++){

            Order order = new Order();
            order.setId((long) i);
            order.setUserId((long)i);
            order.setName("电脑"+i);
            order.setProductId((long)i);
            order.setPrice(new BigDecimal(0.04));
            order.setAmount(i);


            orderService.saveOrUpdate(order);
        }
        return "ok";
    }

    @GetMapping("/get")
    public Order testGetById(){
        long id = 1184489163202789377L;
        Order order = orderService.getById(id);
        System.out.println(order.toString());
        return order;
    }

}
