package com.atguigu.maven;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/19 21:34
 * @Version 1.0
 */
public class ImperialCourtTest {

    class Test1 extends BaseDao<Emp> {

    }
    private Test1 test1 = new Test1();
    @Test
    public void testGetSingleBean() {

        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id = ?";
        Emp singleBean = test1.getSingleBean(sql, 1);
        System.out.println(singleBean);
    }

    @Test
    public void testGetBeanList() {
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp";
        List<Emp> beanList = test1.getBeanList(sql);

        System.out.println(beanList);
    }

    @Test
    public void testUpdate() {
        String sql = "update t_emp set emp_position=? where emp_id=?";
        int emperor = test1.update(sql, "minister", 3);

        System.out.println(emperor);
    }

    @Test
    public void testGetConnection() {
        Connection connection = JDBCUtils.getConnection();

        System.out.println(connection);

        JDBCUtils.releaseConnection(connection);
    }
}
