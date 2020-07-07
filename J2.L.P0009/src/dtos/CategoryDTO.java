/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author nguyentrinhan2000
 */
public class CategoryDTO {
    int  categoryID;
    String categoryname;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryID, String categoryname) {
        this.categoryID = categoryID;
        this.categoryname = categoryname;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Override
    public String toString() {
        return categoryname;
    }
    
}
