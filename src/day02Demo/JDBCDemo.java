package day02Demo;

import day02Demo.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * 测试类
 */
public class JDBCDemo {


    @Test
    public void testQueryById(){
        int id = 2;
        MyConnectionPool myConnectionPool = new MyConnectionPool();
        Connection conn = null;
        try {
            conn = myConnectionPool.getConnection();
            PreparedStatement pstm = conn.prepareStatement("select * from t_user where id = ?");
            pstm.setInt(1 , id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            myConnectionPool.returnConn(conn);
        }
    }

    @Test
    public void testDeleteOrderById(){
        Connection conn = null;
        try {
            conn = C3P0Utils.getConnection();
            PreparedStatement pstm = conn.prepareStatement("delete from t_order where oid = ?");
            pstm.setInt(1 , 1);
            int rows = pstm.executeUpdate();
            if(rows > 0){
                System.out.println("删除订单成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateOrderById(){
        Connection conn = null;
        try {
            conn = DBCPUtils.getConnection();
            PreparedStatement pstm = conn.prepareStatement("update t_order set o_no = ? where oid = ?");
            pstm.setString(1 , "qwe123456");
            pstm.setInt(2 , 2);
            int rows = pstm.executeUpdate();
            if(rows > 0){
                System.out.println("更新订单成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testDBUtils(){
        QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
        /*
            查询操作
            ResultSetHandler:
            BeanListHandler<User> ：自定映射封装对象集合，要求，字段名称与数据库类保持一致
            BeanHandler<User> : 获取到一个对象
            ScalarHandler : 获取到一个数，默认此数为long
         */
        try {
            List<User> userList = qr.query("select * from t_user", new BeanListHandler<>(User.class));
            userList.forEach(u-> System.out.println(u));
            System.out.println("-----------------------------");
            User user = qr.query("select * from t_user where username = ? and password = ?", new BeanHandler<>(User.class), "lisi", "456");
            System.out.println(user);
            System.out.println("-----------------------------");
            Long l = (Long) qr.query("select count(*) from t_user", new ScalarHandler());
            System.out.println(l.intValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
