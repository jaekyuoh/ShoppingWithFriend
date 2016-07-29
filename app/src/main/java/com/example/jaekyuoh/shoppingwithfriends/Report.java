package com.example.jaekyuoh.shoppingwithfriends;

/**
 * Created by jaekyuoh on 3/24/15.
 */
public class Report {
    private String itemName;
    private String price;
    private String location;


    public Report(String itemName, String price, String location){

        this.itemName = itemName;
        this.price = price;
        this.location = location;
    }

    public String getName(){
        return itemName;
    }
    public String getPrice(){
        return price;
    }
    public String getLocation(){
        return location;
    }


    public void setItemName(String n){
        itemName = n;
    }
    public void setPrice(String e){
        price = e;
    }
    public void setLocation(String r){
        location = r;
    }
}
