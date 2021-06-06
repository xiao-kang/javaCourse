package com.jdbc.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author chenxiaokang
 * @date 2021/6/6
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "ID")
    Long id;
    @Column(name = "BookName")
    String bookName;
    @Column(name = "RentTime")
    Date rentTime ;
    @Column(name = "RentMan")
    String rentMan ;

    public Book() {
    }

    public Book(Long id, String bookName, Date rentTime, String rentMan) {
        this.id = id;
        this.bookName = bookName;
        this.rentTime = rentTime;
        this.rentMan = rentMan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public String getRentMan() {
        return rentMan;
    }

    public void setRentMan(String rentMan) {
        this.rentMan = rentMan;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("id=").append(id);
        sb.append(", bookName='").append(bookName).append('\'');
        sb.append(", rentTime=").append(rentTime);
        sb.append(", rentMan='").append(rentMan).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
