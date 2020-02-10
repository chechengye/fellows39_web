package day02Demo;

import day01Demo.utils.JDBCUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyConnectionPool implements DataSource {

    private List<Connection> connections = new ArrayList<>(20);
    /**
     * 连接池的初始化
     */
    public MyConnectionPool(){
        for(int i = 0 ; i < 5 ; i++){
            connections.add(JDBCUtil.getConnection());
        }
    }
    /**
     * 回收方法
     * @return
     * @throws SQLException
     */
    public void returnConn(Connection conn){
        connections.add(conn);
    }
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        if(connections.size() == 0){
            for(int i = 0 ; i < 5 ; i++){
                connections.add(JDBCUtil.getConnection());
            }
        }
        conn = connections.remove(0);
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
