package com.company;

import java.io.Serializable;

public class Equipment implements Serializable {

    //What are you buying? How much is it?
    private String desiredItem = "";
    private Double desiredItemCost = 0.0;

    public Equipment(){
    }
    public Equipment(String itemDesired, Double itemCost){
        desiredItem = itemDesired;
        desiredItemCost = itemCost;
    }
    public String getDesiredItem() {
        return desiredItem;
    }

    public void setDesiredItem(String desiredItem) {
        this.desiredItem = desiredItem;
    }

    public Double getDesiredItemCost() {
        return desiredItemCost;
    }

    public void setDesiredItemCost(Double desiredItemCost) {
        this.desiredItemCost = desiredItemCost;
    }


}
