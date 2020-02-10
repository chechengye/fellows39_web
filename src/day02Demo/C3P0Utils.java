package day02Demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0工具类
 * 这个链接池可以设置最大和最小链接数量,连接等待时间（超时时间）
 * 此连接池持续运行的稳定性很好，在大量并发时（多线程）稳定性有一定的保证。没有连接池监控
 */
public class C3P0Utils {
    private static ComboPooledDataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();//默认会去src根目录下面找到c3p0-config.xml
    }

    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
