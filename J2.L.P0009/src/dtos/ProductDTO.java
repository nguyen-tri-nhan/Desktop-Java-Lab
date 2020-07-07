/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Vector;

/**
 *
 * @author nguyentrinhan2000
 */
public class ProductDTO {
    String productID, productname;
    int price, quantity, categoryID;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productname, int categoryID, int price, int quantity) {
        this.productID = productID;
        this.productname = productname;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
    }

    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public Vector toVector(){
        Vector v = new Vector();

        v.add(productID);
        v.add(productname);
        v.add(price);
        v.add(quantity);
        v.add(categoryID);
        return v;
    }
}
