package com.atguigu.imperial.court.servlet.module;

import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;
import com.atguigu.imperial.court.service.impl.MemorialsServiceImpl;
import com.atguigu.imperial.court.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author hliu
 * @Date 2023/4/26 14:39
 * @Version 1.0
 */
public class WorkServlet extends ModelBaseServlet {
    private MemorialsService memorialsService = new MemorialsServiceImpl();


    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // query the data

        List<Memorials> memorialsList =  memorialsService.getAllMemorialsDigest();

        // store the data to the request
        request.setAttribute("memorialsList", memorialsList);

        // render the view
        String templateName = "memorials-list";
        processTemplate(templateName, request, response);
    }
    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the id from the request

        String id = request.getParameter("memorialsId");

        // query the memorial by the id
        Memorials memorials =  memorialsService.getAllMemorialsDetailById(id);

        //get the status of the memorials
        Integer memorialsStatus = memorials.getMemorialsStatus();

        //check the status
        if (memorialsStatus == 0) {
            //update the status
            memorialsService.updateMemorialsStatusToRead(id);
            memorials.setMemorialsStatus(1);
        }

        //put the memorials into the request scope
        request.setAttribute("memorials", memorials);

        //render the view
        String templateName = "memorials_detail";
        processTemplate(templateName, request, response);
    }

    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the params
        String id = request.getParameter("memorialsId");
        String feedbackContent = request.getParameter("feedbackContent");

        // update the feedback

        memorialsService.updateMemorialsFeedBack(id, feedbackContent);

        // redirect to the view of the memorials

        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
