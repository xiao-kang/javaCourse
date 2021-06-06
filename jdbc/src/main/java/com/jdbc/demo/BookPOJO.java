package com.jdbc.demo;

import java.sql.Date;

/**
 * @author chenxiaokang
 * @date 2021/6/6
 */
public class Book {
    String bookName;
    Date rentTime ;
    String rentMan ;

    public Book() {
    }

    public Book(String bookName, Date rentTime, String rentMan) {
        this.bookName = bookName;
        this.rentTime = rentTime;
        this.rentMan = rentMan;
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
        sb.append("bookName='").append(bookName).append('\'');
        sb.append(", rentTime=").append(rentTime);
        sb.append(", rentMan='").append(rentMan).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
