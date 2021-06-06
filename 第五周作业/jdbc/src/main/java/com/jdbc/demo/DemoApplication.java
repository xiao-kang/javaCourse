package com.jdbc.demo;

import com.jdbc.demo.entity.Book;
import com.jdbc.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    @Autowired
    BookRepo bookRepo;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Book getBook(){
        Book book=bookRepo.getAllById(1L);
        System.out.println("doing");
        System.out.println(book);
        System.out.println(bookRepo.findAll());
        return book;
    }

}
