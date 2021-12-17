package model;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;

    public Item(String name){
        this.name = name;
    }

    public void setItemName(String name){
        this.name = name;
    }

    @Override
    public String toString() {//possibly change the toString to reflect a more simple display of this Item
        return "" + name;
    }
}
