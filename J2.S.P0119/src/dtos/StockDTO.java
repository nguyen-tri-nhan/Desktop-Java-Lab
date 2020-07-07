package dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author nguyentrinhan.dev
 */
public class StockDTO implements Serializable{
    int stockID;
    String stockName = "", address = "", dateAvailable = "", note = "";

    public StockDTO() {
    }
    
    public StockDTO(int stockID, String stockName, String address, String dateAvailable, String note){
        this.stockID = stockID;
        this.stockName = stockName;
        this.address = address;
        this.dateAvailable = dateAvailable;
        this.note = note;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    
    
    public Vector toVector(){
        Vector v = new Vector();

        v.add(stockID);
        v.add(stockName);
        v.add(address);
        v.add(dateAvailable);
        v.add(note);
        
        return v;
    }


    
}
