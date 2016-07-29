package com.example.jaekyuoh.shoppingwithfriends;

/**
 * Created by jaekyuoh on 2/27/15.
 */
public class Friend {
    private String name;
    private String email;
    private final String id;
    private String rating;
    private String friendNumReport;

    public Friend(String id, String name, String email, String rating, String friendNumReport){
        this.id = id;
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.friendNumReport = friendNumReport;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getRating(){
        return rating;
    }
    public String getEmail(){
        return email;
    }
    public String getFriendNumReport(){
        return friendNumReport;
    }

    public void setName(String n){
        name = n;
    }
    public void setEmail(String e){
        email = e;
    }
    public void setRating(String r){
        rating = r;
    }

    public void setFriendNumReport(String nr){
        friendNumReport = nr;
    }

}
