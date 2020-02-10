package day02Demo;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * DBCP连接池
 * 在多并发时，稳定性会有所下降。没有类似于c3p0那种默认的配置文件。若想通过配置文件，需自行定义
 * 可以随意设定池子大小等等
 */
public class DBCPUtils {
    private static BasicDataSource dataSource;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(rb.getString("jdbc.driver"));
        dataSource.setUrl(rb.getString("jdbc.url"));
        dataSource.setUsername(rb.getString("jdbc.username"));
        dataSource.setPassword(rb.getString("jdbc.password"));
    }

    /**
     * 获取数据源
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 获取连接
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
