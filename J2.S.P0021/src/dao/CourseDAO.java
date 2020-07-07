package dao;

import db.DBUtils;
import dto.CourseDTO;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class CourseDAO {

    private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    public CourseDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<CourseDTO> listAllcourse() throws Exception {
        List<CourseDTO> list = null;
        CourseDTO dto = null;
        try {
            String sql = "SELECT Code, Name, Credit FROM Course";
            conn = DBUtils.getMyConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                int credit = rs.getInt("Credit");
                dto = new CourseDTO(code, name, credit);
                list.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean addCourse(CourseDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO Course(Code, Name, Credit) VALUES(?,?,?)";
            conn = DBUtils.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getCode());
            stm.setString(2, dto.getName());
            stm.setInt(3, dto.getCredit());
            stm.executeUpdate();
            check = true;
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public CourseDTO searchByCode(String code) throws Exception {
        CourseDTO dto = null;
        try {
            String sql = "SELECT Code, Name, Credit FROM Course WHERE Code = ?";
            conn = DBUtils.getMyConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, code);
            rs = stm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                int credit = rs.getInt("Credit");
                dto = new CourseDTO(code, name, credit);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return dto;
    }
}
