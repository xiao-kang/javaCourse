package com.chenxk.sharding.demo2;


import com.chenxk.sharding.demo2.generator.domain.Order;
import com.chenxk.sharding.demo2.generator.domain.User;
import com.chenxk.sharding.demo2.generator.service.OrderService;
import com.chenxk.sharding.demo2.generator.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
@MapperScan("com.chenxk.sharding.demo2.generator.mapper")
class Demo2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderService orderService;


    @Test
    public void testSave() {
        for (int i = 0; i < 100; i++) {

            Order order = new Order();
            order.setId((long) i);
            order.setUserId((long) i);
            order.setName("电脑" + i);
            order.setProductId((long) i);
            order.setPrice(new BigDecimal(0.04));
            order.setAmount(i);

            orderService.saveOrUpdate(order);
        }

    }

    @Test
    public void testGetById() {
        Long id = 2L;
        Long userId = 1L;
        Order order = orderService.getById(id);
        System.out.println(order.toString());

    }

    @Autowired
    UserService userService;
    @Test
    public void testUser(){
        for (int i = 0;i < 100;i++){
            User user = new User();
            user.setId((long) i);
            user.setUsername("chenxk"+i);
            user.setEmail("chenxk"+i+"@shanghaitrust.com");
            user.setPassword("aaaaaa"+i);
            user.setUpdateTime(new Date());
            userService.saveOrUpdate(user);
        }

    }


}
