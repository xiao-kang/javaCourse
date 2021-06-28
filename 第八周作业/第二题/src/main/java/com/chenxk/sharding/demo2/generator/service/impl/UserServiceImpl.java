package com.chenxk.sharding.demo2.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenxk.sharding.demo2.generator.domain.User;
import com.chenxk.sharding.demo2.generator.service.UserService;
import com.chenxk.sharding.demo2.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{

}




