package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Emp;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/24 19:25
 * @Version 1.0
 */
public interface EmpService {
    Emp getEmpByLoginAccount(String loginAccount, String password);
}
