package com.example.jaekyuoh.shoppingwithfriends;

/**
 * Created by jaekyuoh on 3/24/15.
 */
public class Item {
    private String itemName;
    private String priceThreshold;
    private String location;


    public Item(String itemName, String priceThreshold, String location){

        this.itemName = itemName;
        this.priceThreshold = priceThreshold;
        this.location = location;
    }

    public String getName(){
        return itemName;
    }
    public String getPriceThreshold(){
        return priceThreshold;
    }
    public String getLocation(){
        return location;
    }


    public void setItemName(String n){
        itemName = n;
    }
    public void setPriceThreshold(String e){
        priceThreshold = e;
    }
    public void setLocation(String r){
        location = r;
    }



}
