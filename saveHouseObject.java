package com.murgray.savehouse;

import java.util.ArrayList;
import java.util.List;

public class saveHouseObject {

    List<Integer> itemID;
    List<String> itemName;
    List<String> brand;
    List<Double> price;
    List<String> type;
    private int size;

    public saveHouseObject(){
        super();
    }

    public saveHouseObject(ArrayList<Integer> itemID,
                           ArrayList<String> itemName,
                           ArrayList<String> brand,
                           ArrayList<Double> price,
                           ArrayList<String> type){
        this.itemID = itemID;
        this.itemName = itemName;
        this.brand = brand;
        this.price = price;
        this.type = type;
        size = this.size();
    }

    public void setItemID(List<Integer> itemID) {
        this.itemID = itemID;
    }

    public List<Integer> getItemID() {
        return itemID;
    }

    public void setItemName(List<String> itemName) {
        this.itemName = itemName;
    }

    public List<String> getItemName() {
        return itemName;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }

    public List<Double> getPrice() {
        return price;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getType() {
        return type;
    }

    public int size(){
        return size;
    }
}
