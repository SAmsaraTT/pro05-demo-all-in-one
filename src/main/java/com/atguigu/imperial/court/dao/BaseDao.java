package com.atguigu.imperial.court.dao;

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/19 21:45
 * @Version 1.0
 */
public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> type = null;
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.type = (Class<T>) actualTypeArguments[0];
    }

    /**
    * @Description: get the single object
    * @Param: [sql, params]
    * @return: T
    * @Author: hliu
    * @Date: 2023/4/19
    */

    public T getSingleBean(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        T t = null;
        try {
            t = queryRunner.query(connection, sql, new BeanHandler<T>(type), params);

            return t;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
    * @Description: get a list of query result
    * @Param: [sql, params]
    * @return: java.util.List<T>
    * @Author: hliu
    * @Date: 2023/4/19
    */

    public List<T> getBeanList(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        List<T> list = null;

        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<T>(type), params);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * @Description: general update method
    * @Param: [sql, params]
    * @return: int
    * @Author: hliu
    * @Date: 2023/4/19
    */

    public int update(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
