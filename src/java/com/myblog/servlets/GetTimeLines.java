package com.myblog.servlets;

import java.io.IOException;
import java.util.ArrayList;

import com.myblog.dao.TimeLineDao;
import com.myblog.entities.TimeLine;
import com.myblog.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Devender
 */
@WebServlet(name = "GetTimeLines", urlPatterns = { "/gettimelines" })
public class GetTimeLines extends HttpServlet {

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
        System.out.println("Hello I getTimeLines servlet");
        response.setContentType("application/json");
        User user = (User) request.getSession().getAttribute("user");
        int userIdLong = user.getUser_id();

        ArrayList<TimeLine> timeLines = TimeLineDao.getTimeLinesOfUser(userIdLong);
        String jsonString = "{\"success\":true, \"TimeLines\":[";
        for (int i = 0; i < timeLines.size(); i++) {
            jsonString += "{";
            jsonString += "\"id\":" + "\"" + String.valueOf(timeLines.get(i).getTimelineId()) + "\",";
            jsonString += "\"project_id\":" + "\"" + String.valueOf(timeLines.get(i).getProjectId()) + "\",";
            jsonString += "\"startTime\":" + "\"" + String.valueOf(timeLines.get(i).getStarDate().getTime()) + "\",";
            jsonString += "\"endTime\":" + "\"" + String.valueOf(timeLines.get(i).getEnDate().getTime()) + "\"";
            jsonString += "}";
            if (i != timeLines.size()-1) {
                jsonString += ",";
            }
        }
        jsonString += "]}";
        response.getWriter().write(jsonString);

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
