package com.atguigu.imperial.court.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description the utility for JDBC
 * @Author hliu
 * @Date 2023/4/19 17:49
 * @Version 1.0
 */
public class JDBCUtils {
    // set the field to be static
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @Description: get the connection
    * @Param: []
    * @return: java.sql.Connection
    * @Author: hliu
    * @Date: 2023/4/19
    */
    public static Connection getConnection() {
        //check if the connection exists
        Connection connection = null;
        try {
            connection = threadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();

                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    /**
    * @Description: close the connection
    * @Param: []
    * @return:
    * @Author: hliu
    * @Date: 2023/4/19
    */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();

                threadLocal.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
