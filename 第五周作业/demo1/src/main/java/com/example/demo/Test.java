package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author chenxiaokang
 * @date 2021/6/5
 */
@Component
public class Test {
    @Autowired
    io.kimmking.spring02.School school;
    @Bean
    public Test getTest(){
        System.out.println(school);
        return new Test();
    }
}
