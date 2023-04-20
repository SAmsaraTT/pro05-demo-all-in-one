package com.atguigu.maven;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/19 21:34
 * @Version 1.0
 */
public class ImperialCourtTest {

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtils.getConnection();

        System.out.println(connection);

        JDBCUtils.releaseConnection(connection);
    }
}
