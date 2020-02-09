package day01Demo.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 自行抽取的JDBC工具类
 */
public class JDBCUtil {
    private static String diver;
    private static String url;
    private static String username;//连接mysql数据库的用户名
    private static String password;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        diver = rb.getString("jdbc.driver");
        url = rb.getString("jdbc.url");
        username = rb.getString("jdbc.username");
        password = rb.getString("jdbc.password");
    }

    /**
     * 获取连接的方法
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(diver);
            conn = DriverManager.getConnection(url , username , password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     */
    public static void releaseRes(ResultSet rs , Statement st , Connection conn){
        if(null != rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != st){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
