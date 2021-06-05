package io.kimmking.spring02.bean;

import io.kimmking.spring01.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chenxiaokang
 * @date 2021/6/5
 */
@Component
public class BeanProvider {
    @Bean(name = "student900",value = "student900")
    public Student student1(){
        Student student=new Student(900, "chenxk900");


        return  student;
    }

    @Bean(name = "student901")
    public Student student2(){
        Student student=new Student(901, "chenxk901");
        return  student;
    }

}
