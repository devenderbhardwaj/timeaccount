package com.myblog.servlets;

import com.myblog.dao.UserDao;
import com.myblog.entities.User;
import com.myblog.helper.InfoIncompleteException;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Devender
 */
@WebServlet(name = "SaveUser", urlPatterns = { "/saveUser" })
public class SaveUser extends HttpServlet {

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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String check = request.getParameter("terms");
        if (check == null) {
            response.getWriter().write("{\"success\":false,\"message\":\"Accept terms and conditions to proceed\"}");
        } else {
            try {

                User user = new User(name, email, password, gender);
                if (UserDao.insertUser(user)) {
                    response.getWriter().write(
                            "{\"success\":true,\"message\":\"Registered Successfully. You can login from login page\"}");
                } else {
                    response.getWriter().write("{\"success\":false,\"message\":\"Some Problem Occured\"}");
                }

            } catch (InfoIncompleteException e) {
                response.getWriter().write("{\"success\":false,\"message\":\"Server find information incomplete\"}");
            } catch (SQLIntegrityConstraintViolationException e) {
                response.getWriter().write("{\"success\":false,\"message\":\"User already exist\"}");
            }
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
