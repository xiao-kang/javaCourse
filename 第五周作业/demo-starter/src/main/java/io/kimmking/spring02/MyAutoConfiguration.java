package io.kimmking.spring02;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author chenxiaokang
 * @date 2021/6/5
 */
@Configuration
//@ConditionalOnClass({Student.class, Klass.class})
@EnableConfigurationProperties(SchoolProperties.class)
public class MyAutoConfiguration {
    @Resource
    SchoolProperties schoolProperties;
    @Bean
    @ConditionalOnProperty(prefix = "school")
    public School school(){
        School school=new School();
        school.setClass1(schoolProperties.klass);
        return school;
    }
}
