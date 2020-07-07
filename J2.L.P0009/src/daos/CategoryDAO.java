/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import db.DBUtils;
import dtos.CategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguyentrinhan2000
 */
public class CategoryDAO implements Serializable{

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public List<CategoryDTO> loadCategory() throws Exception {
        List<CategoryDTO> list = null;
        CategoryDTO dto = null;

        try {
            String sql = "SELECT CategoryID, CategoryName FROM tblCategory";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                list.add(new CategoryDTO(categoryID, categoryName));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public CategoryDTO findByCategoryName(String categoryName) throws Exception {
        CategoryDTO dto = null;

        try {
            String sql = "SELECT CategoryID FROM tblCategory WHERE CategoryName = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, categoryName);
            rs = stm.executeQuery();
            if (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                dto = new CategoryDTO(categoryID, categoryName);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public CategoryDTO findByCategoryID(int categoryID) throws Exception {
        CategoryDTO dto = null;

        try {
            String sql = "SELECT CategoryName FROM tblCategory WHERE CategoryID = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, categoryID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String categoryName = rs.getString("CategoryName");
                dto = new CategoryDTO(categoryID, categoryName);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateCategory(CategoryDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "UPDATE tblCategory SET CategoryName = ? WHERE CategoryID = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getCategoryname());
            stm.setInt(2, dto.getCategoryID());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteCategory(String categoryName) throws Exception {
        boolean check = false;

        try {
            String sql = "DELETE FROM tblCategory WHERE CategoryName = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, categoryName);
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkDataOnCategory(String categoryName) throws Exception {
        //SELECT P.ProductID FROM tblCategory C, tblProduct P WHERE C.CategoryID = P.CategoryID AND C.CategoryName = 'Network Device'
        try {
            String sql = "SELECT P.ProductID FROM tblCategory C, tblProduct P "
                    + "WHERE C.CategoryID = P.CategoryID AND C.CategoryName = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, categoryName);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean insertCategory(CategoryDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "INSERT INTO tblCategory(CategoryID, CategoryName) VALUES(?,?)";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, dto.getCategoryID());
            stm.setString(2, dto.getCategoryname());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
