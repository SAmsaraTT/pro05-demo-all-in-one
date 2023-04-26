package com.atguigu.imperial.court.filter;

import com.atguigu.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/20 20:43
 * @Version 1.0
 */

public class TransactionFilter implements Filter {
    //use a set to store the static resource
    private static Set<String> staticSet;

    static {
        staticSet = new HashSet<>();
        staticSet.add(".png");
        staticSet.add(".jpg");
        staticSet.add(".css");
        staticSet.add(".js");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String servletPath = httpServletRequest.getServletPath();
        if(servletPath.contains(".")) {
            String substring = servletPath.substring(servletPath.lastIndexOf("."));
            if (staticSet.contains(substring)) {
                filterChain.doFilter(servletRequest, servletResponse);

                return;
            }
        }



        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            filterChain.doFilter(servletRequest, servletResponse);
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String msg = e.getMessage();
            httpServletRequest.setAttribute("systemMessage", msg);
            httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, servletResponse);

        } finally {
            JDBCUtils.releaseConnection(connection);
        }
    }

    @Override
    public void destroy() {

    }
}
