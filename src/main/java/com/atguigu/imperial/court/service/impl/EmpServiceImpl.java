package com.atguigu.imperial.court.service.impl;

import com.atguigu.imperial.court.dao.api.EmpDao;
import com.atguigu.imperial.court.dao.impl.EmpDaoImpl;
import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.exception.LoginFailedException;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.util.ImperialCourtConst;
import com.atguigu.imperial.court.util.MD5Util;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/24 19:26
 * @Version 1.0
 */
public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String password) {
        // encrypt the password
        String encode = MD5Util.encode(password);

        // query the database
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount, encode);

        if (emp != null) {
            return emp;
        } else {
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
