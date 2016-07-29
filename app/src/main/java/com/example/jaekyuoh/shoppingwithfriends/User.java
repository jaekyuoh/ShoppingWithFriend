package com.example.jaekyuoh.shoppingwithfriends;

/**
 * Created by jaekyuoh on 2/27/15.
 */
public class User {
    private String name;
    private String password;
    private String email;
    private final String id;
    private String numReport;
    

    public User(String id, String name, String password, String email, String numReport){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.numReport = numReport;
    }

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public String getNumReport(){
        return numReport;
    }

    public void setName(String n){
        name = n;
    }
    public void setPassword(String p){
        password = p;
    }
    public void setNumReport(String nr){
        numReport = nr;
    }
    public void setEmail(String e){
        email = e;
    }

    //add friend
    //delete friend
    //add item
    //delete item
    //editFriendScore
    //add report

}
