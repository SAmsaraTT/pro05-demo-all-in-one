package com.atguigu.imperial.court.dao.impl;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.dao.api.EmpDao;
import com.atguigu.imperial.court.entity.Emp;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/20 20:37
 * @Version 1.0
 */
public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {
    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encode) {
        String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where login_account = ? and login_password = ?";

        Emp singleBean = getSingleBean(sql, loginAccount, encode);
        return singleBean;
    }
}
