package com.myblog.servlets;

import java.io.IOException;
import java.util.ArrayList;

import com.myblog.dao.ProjectDao;
import com.myblog.entities.Project;
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
@WebServlet(name = "GetProjects", urlPatterns = { "/getProjects" })
public class GetProjects extends HttpServlet {

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
        User user = (User) request.getSession().getAttribute("user");

        ArrayList<Project> projects = ProjectDao.getProjects(user.getUser_id());
        String string = "[";
        for (int i = 0; i < projects.size(); ++i) {
            string += "{";
            string += "\"id\":" + projects.get(i).getProjectId() + "," ;
            string += "\"projectName\":"+"\"" + projects.get(i).getProjectName() + "\"";
            string += "}";
            if (i != projects.size() - 1) {
                string += ",";
            }
        }
        string += "]";
        response.getWriter().write(string);
    }

    
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
