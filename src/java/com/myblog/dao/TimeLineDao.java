
package com.myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import com.myblog.entities.Project;
import com.myblog.entities.TimeLine;
import com.myblog.helper.ConnectionProvider;

/**
 *
 * @author Devender
 */
public class TimeLineDao {
    private static Connection con  = ConnectionProvider.getConnection();

    public static boolean insertTimeLine(TimeLine timeLine) {
        boolean flag = false;
        String query = "insert into timeline (startTime, endTime, project_id) VALUES (?, ? , ?)";
        try {
            PreparedStatement pStatement = TimeLineDao.con.prepareStatement(query);
            pStatement.setTimestamp(1, new Timestamp(timeLine.getStarDate().getTime()));
            pStatement.setTimestamp(2, new Timestamp(timeLine.getEnDate().getTime()));
            pStatement.setInt(3, timeLine.getProjectId());
            pStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    public static  ArrayList<TimeLine> getTimeLinesOfUser(int userid) {
        ArrayList<TimeLine> timeLines = new ArrayList<>();
        
        ArrayList<Project> projects = ProjectDao.getProjects(userid);
        
        for (Project project : projects) {
            String query = "select * from timeline where project_id=" + "'" + String.valueOf(project.getProjectId()) + "'";
            
            try {
                PreparedStatement preparedStatement = TimeLineDao.con.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    TimeLine t = new TimeLine();
                    t.setStarDate(new Date(rs.getTimestamp("startTime").getTime()));
                    t.setEnDate(new Date(rs.getTimestamp("endTime").getTime()));
                    t.setTimelineId(rs.getInt("timeline_id"));
                    t.setProjectId(rs.getInt("project_id"));
                    timeLines.add(t);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }
        }

        timeLines.sort(new Comparator<TimeLine>() {
            public int compare(TimeLine t1, TimeLine t2) {
                return t1.getTimelineId() - t2.getTimelineId();
            }
        });

        return timeLines;
    }
}
