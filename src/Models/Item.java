/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class Item {

    private int index;
    private Long id;
    private String description;

    public Item(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }
    
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    public String toString() {
            return description;
    }
}
