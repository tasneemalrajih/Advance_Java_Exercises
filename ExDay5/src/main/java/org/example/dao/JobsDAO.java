package org.example.dao;

import org.example.models.jobs;

import java.sql.*;
import java.util.ArrayList;

public class JobsDAO {
    private static final String url = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\SDAIA-Java-Course\\JavaBasics\\Exercises\\hr.db";
    private static final String SELECT_ALL_JOB = "select * from jobs";
    private static final String SELECT_ONE_JOB = "select * from jobs where department_id = ?";
    private static final String INSERT_JOB = "insert into jobs values (?,?, ?, ?)";
    private static final String UPDATE_JOB = "update jobs set department_name = ?, location_id = ? where department_id = ?";
    private static final String DELETE_JOB = "delete from jobs where department_id = ?";

    public void insertjob(jobs j ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement st = conn.prepareStatement(INSERT_JOB);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3,j.getMax_salary());
        st.setDouble(4,j.getMin_salary());
        st.executeUpdate();
    }

    public void updatejob(jobs j ) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOB);
        st.setInt(1,j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3,j.getMax_salary());
        st.setDouble(4,j.getMin_salary());
        ;
        st.executeUpdate();
    }

    public void deletjob(int job_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement st = conn.prepareStatement(DELETE_JOB);
        st.setInt(1, job_id);
        st.executeUpdate();
    }

    public jobs selectjob(int job_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOB);
        st.setInt(1, job_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new jobs(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<jobs> selectAlljob() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOB);
        ResultSet rs = st.executeQuery();
        ArrayList<jobs> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new jobs(rs));
        }

        return jobs;
    }

}
