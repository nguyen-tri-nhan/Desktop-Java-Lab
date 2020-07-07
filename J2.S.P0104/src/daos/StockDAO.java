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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DBUtils;
import dtos.StockDTO;

/**
 *
 * @author nguyentrinhand.dev
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

    public List<StockDTO> loadAllRecord() throws Exception {
        List<StockDTO> list = null;

        StockDTO dto = null;

        try {
            String sql = "USE FU_DB;SELECT StockID , StockName , Address , DateAvailable , Note FROM Stocks";
            conn = DBUtils.getMyConnection();
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
