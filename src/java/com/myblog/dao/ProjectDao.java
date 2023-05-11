package com.myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.myblog.entities.Project;
import com.myblog.helper.ConnectionProvider;
import com.myblog.helper.DoesNotExistException;
import com.myblog.helper.InfoIncompleteException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ProjectDao {
    private static final Connection con = ConnectionProvider.getConnection();

    public static boolean insertProject(String name, int userId) throws SQLIntegrityConstraintViolationException {
        boolean flag = false;
        String query = "insert into project ( project_name, user_id) values(?,?)";
        try {
            PreparedStatement pStatement = ProjectDao.con.prepareStatement(query);
            pStatement.setString(1, name);
            pStatement.setInt(2, userId);
            pStatement.executeUpdate();
            flag = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    public static boolean insertProject(Project project) throws InfoIncompleteException, SQLIntegrityConstraintViolationException  {
        if (project.getProjectName() == null || project.getProjectUserId() == 0) {
            throw new InfoIncompleteException("Project details not suffiencent");
        } else {
            return insertProject(project.getProjectName(), project.getProjectUserId());
        }

    }

    public static Project getProject(int projectId) throws DoesNotExistException {
        Project project = null;
        String query = "select * from project where project_id=" + "'" + projectId + "'";

        try {
            PreparedStatement pStatement = ProjectDao.con.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectUserId(resultSet.getInt("user_id"));
            } else {
                throw new DoesNotExistException("Project does not exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return project;
    }

    public static Project getProject(String pName, int userId) throws DoesNotExistException  {
        Project project = null;
        String query = "select * from project where project_name=" + "'" + pName + "' AND user_id="  + userId ;

        try {
            PreparedStatement pStatement = ProjectDao.con.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectUserId(resultSet.getInt("user_id"));
            } else {
                throw new DoesNotExistException("Project does not exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return project;
    }

    public static ArrayList<Project> getProjects(int ownerId) {
        ArrayList<Project> projects = new ArrayList<>();
        String query = "select * from project where user_id=" + "'" + ownerId + "'"  ;
        try {
            PreparedStatement pStatement = ProjectDao.con.prepareStatement(query);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectUserId(resultSet.getInt("user_id"));
                projects.add(project);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projects;
    }

}
