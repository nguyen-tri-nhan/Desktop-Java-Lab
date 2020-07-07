/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import db.DBUtils;
import dtos.StockDTO;

/**
 *
 * @author nguyentrinhan.dev
 */
public class StockDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public StockDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean createDB() throws Exception {
        boolean check = false;

        try {
            String sql = "CREATE DATABASE FU_DB_new1";
            conn = DBUtils.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.executeUpdate();
            check = true;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createTable() throws Exception {
        boolean check = false;

        try {
            String sql = "USE FU_DB_new1;CREATE TABLE Stocks (StockID int NOT NULL PRIMARY KEY, StockName nvarchar(50), Address nvarchar(50), DateAvailable date, Note nvarchar(50),Quantity int);";
            conn = DBUtils.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.executeUpdate();
            check = true;           
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean insertData() throws Exception {
        boolean check = false;

        try {
            String sql = "USE FU_DB_new1;\n" +
                        "INSERT INTO Stocks (StockID, StockName, Address, DateAvailable, Note) VALUES (1, 'Stock one', 'No1 - Wasington street', '11/05/2010', '');\n" +
                        "INSERT INTO Stocks (StockID, StockName, Address, DateAvailable, Note) VALUES (2, 'Stock two', '372 Cave town - 001 Banks', '09/07/2011', '');\n" +
                        "INSERT INTO Stocks (StockID, StockName, Address, DateAvailable, Note) VALUES (3, 'Stock three', 'Nary angel - 890 Number one', '05/13/2010', '');\n" +
                        "INSERT INTO Stocks (StockID, StockName, Address, DateAvailable, Note) VALUES (4, 'Stock four', 'Twin tower - 01 Main street', '04/07/2015', '');\n" +
                        "INSERT INTO Stocks (StockID, StockName, Address, DateAvailable, Note) VALUES (5, 'Stock five', 'Victory anniversary district', '08/12/2014', '');";
            conn = DBUtils.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.executeUpdate();
            check = true;           
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<StockDTO> loadAllRecord() throws Exception {
        List<StockDTO> list = null;

        StockDTO dto = null;

        try {
            String sql = "USE FU_DB_new1;SELECT StockID , StockName , Address , DateAvailable , Note FROM Stocks";
            conn = DBUtils.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int stockID = rs.getInt("StockID");
                String stockName = rs.getString("StockName");
                String address = rs.getString("Address");
                String dateAvailable = rs.getDate("DateAvailable").toString();
                String note = rs.getString("Note");
                dto = new StockDTO(stockID, stockName, address, dateAvailable, note);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
}
