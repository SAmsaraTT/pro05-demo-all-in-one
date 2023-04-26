package com.atguigu.imperial.court.servlet.module;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.exception.LoginFailedException;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.service.impl.EmpServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;
import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/24 19:19
 * @Version 1.0
 */
public class AuthServlet extends ModelBaseServlet {
    private EmpService empService = new EmpServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1. get the params
            String loginAccount = request.getParameter("loginAccount");
            String password = request.getParameter("loginPassword");

            //2. invoke the method in the EmpService
            Emp emp = empService.getEmpByLoginAccount(loginAccount, password);

            //3. get the session from the request
            HttpSession session = request.getSession();

            //4. put the Emp into the Session
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);

            //5. enter the corresponding view

//            String templateName = "temp";
//            processTemplate(templateName, request ,response);

            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof LoginFailedException) {
                request.setAttribute("message", e.getMessage());
                processTemplate("index", request, response);
            } else {
                throw new RuntimeException(e);
            }
        }
    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the http session
        HttpSession session = request.getSession();

        // invalidate the session
        session.invalidate();

        // back to the index
        String templateName = "index";
        processTemplate(templateName, request, response);
    }
}
