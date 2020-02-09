package day01Demo;

import day01Demo.utils.JDBCUtil;
import org.junit.Test;

import java.sql.*;

/**
 * JDBC相关使用
 */
public class JDBCDemo {

    @Test
    public void testQuery(){
        ResultSet rs = null;
        Statement st = null;
        Connection conn = null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接对象根据URL、用户名、密码
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fellows39", "root", "root");
            //3、获取Statement对象。可用来执行sql语句
            st = conn.createStatement();
            //4、编写相关的sql语句
            String sql = "select * from t_user";
            //5、用Statement对象执行sql语句，若查询操作，那返回结果集。若是更新操作，返回受影响行数
            rs = st.executeQuery(sql);
            //6、遍历结果集
            while (rs.next()){//索引指向下一个，并返回有没有，若有则true，否则false
                //若是通过，列索引获取，注意索引值从1开始
                System.out.println("ID = " + rs.getInt("id")
                    + "用户名 = " + rs.getString(2)
                    + "密码 = " + rs.getString("password"));//一切皆串，索引，不确定数据库字段类型的时候，我们可以统一使用getString()方法

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    @Test
    public void testUpdate(){
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接对象根据URL、用户名、密码
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fellows39?useUnicode=true&characterEncoding=UTF-8", "root", "root");
            //3、获取Statement对象。可用来执行sql语句
            Statement st = conn.createStatement();
            //4、编写相关的sql语句
            String sql = "update t_user set username = 'wangwu' where id = 1";
            //5、用Statement对象执行sql语句，若查询操作，那返回结果集。若是更新操作，返回受影响行数
            int rows = st.executeUpdate(sql);
            if(rows > 0){
                System.out.println("更新成功!");
            }else{
                System.out.println("更新失败!");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDelete(){
        Connection conn = null;
        Statement st = null;
        try {
           conn = JDBCUtil.getConnection();
           st = conn.createStatement();
           String sql = "delete from t_user where id = 1";
           int rows = st.executeUpdate(sql);
           if(rows > 0){
               System.out.println("删除成功!");
           }else{
               System.out.println("删除失败!");
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.releaseRes(null , st , conn);
        }
    }

    @Test
    public void testLogin(){
        //lisi'or'zhangsanss不是真实存在的用户名，sql片段引起的问题。SQL进行攻击
        login("lisi" , "456");
    }

    /**
     * 处理登陆操作的方法
     * @param username
     * @param password
     */
    private void login(String username, String password) {
        Connection conn = null;
        //Statement st = null;
        //PreparedStatement是Statement子类 。1、可以防止SQL攻击 2、提高可读性 3、提高效率，生成对象时，就已经将sql语句放入对象中了，所以它会进行预编译。
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            //String sql = "select * from t_user where username = '"+username+"' and password = '"+password+"'";//where条件有多个需要判断时使用and做连接
            String sql = "select * from t_user where username =  ? and password = ? ";//?在sql语句中起到了占位符的作用
            pstm = conn.prepareStatement(sql);
            pstm.setString(1 , username);//有api可以动态存放参数。索引位置也是从1开始
            pstm.setString(2 , password);
            //rs = st.executeQuery(sql);
            rs = pstm.executeQuery();//调用不需要传入参数的执行方法
            System.out.println(sql);
            if(rs.next()){
                System.out.println("欢迎," + username);
            }else{
                System.out.println("用户名或密码错误!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.releaseRes(rs , pstm , conn);
        }
    }
}
