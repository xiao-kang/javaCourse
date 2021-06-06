package com.jdbc.demo.repo;

import com.jdbc.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chenxiaokang
 * @date 2021/6/6
 */
public interface BookRepo extends JpaRepository<Book,Long>{
    Book getAllById(Long id);
    List<Book> findAll();
}

