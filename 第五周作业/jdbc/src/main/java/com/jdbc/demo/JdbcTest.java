package com.jdbc.demo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author chenxiaokang
 * @date 2021/6/6
 */

public class JdbcTest {

    private static final String URL = "jdbc:mysql://192.168.52.125:3306/becker?characterEncoding=utf8";
    private static final String USER = "becker";
    private static final String PASSWORD = "becker125";
    private static Connection conn = null;
    String driver = "com.mysql.jdbc.Driver";

    public void getConn() throws SQLException {
        try {
            //1、加载驱动
            Class.forName(driver);
            //2、获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<BookPOJO> queryBookList() {
        String sql = "select * from BOOaK";
        List<BookPOJO> list = new ArrayList<BookPOJO>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                String bookName = rs.getString("BookName");
                Date rentTime = rs.getDate("RentTime");
                String rentMan = rs.getString("RentMan");
                System.out.println("书名："+bookName+" 借阅时间："+rentTime+" 借阅人："+rentMan);
                list.add(new BookPOJO(bookName,rentTime,rentMan));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addBook(BookPOJO bookPOJO) {
        String addContext = "insert into BOOK(BookName,RentTime,RentMan) values (?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(addContext);
            pst.setString(1, bookPOJO.getBookName());
            pst.setDate(2, bookPOJO.getRentTime());
            pst.setString(3, bookPOJO.getRentMan());
            pst.execute();

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String bookName) {
        String sql = "delete from BOOK where BookName = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,bookName);
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyBook(BookPOJO bookPOJO) {
        int i = 0;
        String sql = "update BOOK set RentTime = ?,RentMan = ? where BookName = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDate(1, bookPOJO.getRentTime());
            pst.setString(2, bookPOJO.getRentMan());
            pst.setString(3, bookPOJO.getBookName());
            pst.execute();
            pst.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JdbcTest jdbcTest = new JdbcTest();
        try {
            jdbcTest.getConn();
            System.out.println("请输入要进行的操作 add a,delete d ,update u,query q");
            Scanner scanner = new Scanner(System.in);
            String s= scanner.next();
            if (s.equals("a")){
                jdbcTest.addBook(new BookPOJO("1", Date.valueOf("2009-12-11"),"3"));
            }else if (s.equals("u")){
                jdbcTest.modifyBook(new BookPOJO("1", Date.valueOf("2019-12-11"),"4"));
            }else if (s.equals("d")){
                jdbcTest.deleteBook("1");
            }else if (s.equals("q")){
                jdbcTest.queryBookList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



