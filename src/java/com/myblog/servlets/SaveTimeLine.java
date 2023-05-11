package com.myblog.servlets;

import java.io.IOException;
import java.util.Date;

import com.myblog.dao.TimeLineDao;
import com.myblog.entities.TimeLine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Devender
 */
@WebServlet(name = "SaveTimeLine", urlPatterns = { "/saveTimeLine" })
public class SaveTimeLine extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        if (request.getSession().getAttribute("user") != null) {
            Long starTime = Long.valueOf(request.getParameter("startime"));
            Long endTime = Long.valueOf(request.getParameter("endtime"));
            int projectId = Integer.valueOf(request.getParameter("projectid"));
            TimeLine timeLine = new TimeLine(projectId, new Date(starTime), new Date(endTime));

            if (TimeLineDao.insertTimeLine(timeLine)) {
                response.getWriter().write("{\"success\":true}");
                System.out.println("I have sent success : true");

            } else {
                System.out.println("I have sent success : false");

                response.getWriter().write("{\"success\":false}");
            }
        } else {
            System.out.println("user is null");
            response.getWriter().write("{\"success\":false}");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
