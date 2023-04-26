package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Emp;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/20 20:37
 * @Version 1.0
 */
public interface EmpDao {
    Emp selectEmpByLoginAccount(String loginAccount, String encode);
}
