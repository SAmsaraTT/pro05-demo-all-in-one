package com.atguigu.imperial.court.filter;

import com.atguigu.imperial.court.util.ImperialCourtConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/26 17:47
 * @Version 1.0
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // get the session
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        Object attribute = session.getAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME);

        // check if login is null

        if (attribute != null) {
            filterChain.doFilter(httpServletRequest, servletResponse);
            return;
        }

        //back to the index

        httpServletRequest.setAttribute("systemMessage", ImperialCourtConst.ACCESS_DENIED_MESSAGE);
        httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
