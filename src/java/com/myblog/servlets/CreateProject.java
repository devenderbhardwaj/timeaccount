package com.myblog.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.myblog.dao.ProjectDao;
import com.myblog.entities.Project;
import com.myblog.entities.User;
import com.myblog.helper.DoesNotExistException;
import com.myblog.helper.InfoIncompleteException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 *
 * @author Devender
 */
@WebServlet(name = "CreateProject", urlPatterns = {"/createProject"})
public class CreateProject extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                System.out.println("welcome to create project");
        response.setContentType("application/json");
        String projectName = request.getParameter("projectname");
        User user = (User) request.getSession().getAttribute("user");
        int userId = (user.getUser_id());
        Project project = new Project(projectName, userId);
        try {
            if (ProjectDao.insertProject(project)) {
                project = null;
                try {
                    project = ProjectDao.getProject(projectName, userId);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"success\": true, \"message\":\"Project saved in database\",\"project\":{" + "\"id\":" + project.getProjectId() + "," +"\"projectName\":" + "\"" + project.getProjectName() + "\"" +"}}");
                } catch (DoesNotExistException e) {
                    response.getWriter().write("{\"success\": true, \"message\":\"Project can't be fetched back from database\",\"project\":{}}");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
            response.getWriter().write("{\"success\": false, \"message\":\"Project already exist\"}");
        } catch (InfoIncompleteException e) {
            System.out.println(e.getMessage());
            response.getWriter().write("{\"success\": false, \"message\":\"Server does not enough information for operation\"}");
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
