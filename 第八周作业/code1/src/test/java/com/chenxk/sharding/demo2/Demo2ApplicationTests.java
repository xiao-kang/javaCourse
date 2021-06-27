package com.chenxk.sharding.demo2;


import com.chenxk.sharding.demo2.mybatis.domain.Order;
import com.chenxk.sharding.demo2.mybatis.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.chenxk.sharding.demo2.db.mapper")

class Demo2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void testSave(){
        for (int i = 0 ; i< 100 ; i++){

            Order order = new Order();
            order.setId((long) i);
            order.setUserId((long)i);
            order.setName("电脑"+i);

            orderMapper.insertSelective(order);
        }
    }

    @Test
    public void testGetById(){
        long id = 1184489163202789377L;
        Order order = orderMapper.selectByPrimaryKey(id);
        System.out.println(order.toString());
    }

}
