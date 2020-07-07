/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import db.DBUtils;
import dtos.ProductDTO;
import java.util.List;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nguyentrinhan2000
 */
public class ProductDAO implements Serializable {

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

    public List<ProductDTO> loadAllProduct() throws Exception {
        List<ProductDTO> list = null;
        ProductDTO dto = null;
        try {
            String sql = "SELECT ProductID, ProductName, Price, Quantity, CategoryID FROM tblProduct";
            //SELECT p.ProductID,p.ProductName,p.Price,p.Quantity,c.CategoryName FROM tblCategory C, tblProduct P WHERE C.CategoryID = P.CategoryID
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                int categoryID = rs.getInt("CategoryID");
                dto = new ProductDTO(productID, productName, categoryID, price, quantity);
                list.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ProductDTO> loadProductByCate(String categoryName) throws Exception {
        List<ProductDTO> list = null;
        ProductDTO dto = null;
        try {
            String sql = "SELECT p.ProductID,p.ProductName,p.Price,p.Quantity,p.CategoryID FROM tblCategory C, tblProduct P WHERE C.CategoryID = P.CategoryID AND C.CategoryName = ?";
            //SELECT p.ProductID,p.ProductName,p.Price,p.Quantity,c.CategoryName FROM tblCategory C, tblProduct P WHERE C.CategoryID = P.CategoryID
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, categoryName);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String productID = rs.getString("ProductID");
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                int categoryID = rs.getInt("CategoryID");
                dto = new ProductDTO(productID, productName, categoryID, price, quantity);
                list.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public ProductDTO findByProductID(String productID) throws Exception {
        ProductDTO dto = null;

        try {
            String sql = "SELECT ProductID, ProductName, Price, Quantity, CategoryID FROM tblProduct WHERE ProductID=?";
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String productName = rs.getString("ProductName");
                int price = rs.getInt("Price");
                int quantity = rs.getInt("Quantity");
                int categoryID = rs.getInt("CategoryID");
                dto = new ProductDTO(productID, productName, categoryID, price, quantity);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }

        return dto;
    }

    public boolean insertProduct(ProductDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "INSERT INTO tblProduct (ProductID, ProductName, Price, Quantity, CategoryID) VALUES(?,?,?,?,?)";
            con = db.DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getProductID());
            stm.setString(2, dto.getProductname());
            stm.setInt(3, dto.getPrice());
            stm.setInt(4, dto.getQuantity());
            stm.setInt(5, dto.getCategoryID());
            stm.executeUpdate();
            check = true;
        } catch (Exception e) {
            System.out.println(e.toString());
            check = false;
        } finally {
            closeConnection();
        }

        return check;
    }
    public boolean updateProduct(ProductDTO dto) throws Exception {
        boolean check = false;

        try {
            String sql = "UPDATE tblProduct SET ProductName = ?, Price = ?, Quantity = ?, CategoryID = ?"
                    + " WHERE ProductID = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getProductname());
            stm.setInt(2, dto.getPrice());
            stm.setInt(3, dto.getQuantity());
            stm.setInt(4, dto.getCategoryID());
            stm.setString(5, dto.getProductID());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean deleteProduct(String productName) throws Exception {
        boolean check = false;

        try {
            String sql = "DELETE FROM tblProduct WHERE ProductName = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, productName);
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
