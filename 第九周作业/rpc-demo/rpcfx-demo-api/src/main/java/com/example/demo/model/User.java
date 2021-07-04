package com.example.demo.model;

import lombok.Data;

/**
 * @author chenxk
 */
@Data
public class User {

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
