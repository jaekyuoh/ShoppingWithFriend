package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *this class represents Homepage for this app after user has logged in
 * @author Jaekyu Oh
 */


public class HomepageActivity extends ActionBarActivity {
    /** onCreate() shows the layout that is instructed in activity_homepage.xml on screen*/
    private String id;
    private Boolean firstLogin;
    private static final String PREFS = "credentialPrefs";
    private static final String ITEMPREFS = "itemData";
    private static final String FRIENDSLIST = "friendsData";  // this is the friends lists holder
    private static final String REPORTPREFS = "reportData";  // this is the report holder

    private SharedPreferences itemList;
    private SharedPreferences friendsList;
    private SharedPreferences userList;
    private SharedPreferences reportList;
    private String friendId;
    private ArrayList<String> availableItems; //contains now available items and passed onto AvailableItemsActivity. item|price|location



    //    public static final String PREFS = "credentialPrefs";
    //Bundle extras;
    //String token; //current userID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        userList = getSharedPreferences(PREFS,0);

        id = getIntent().getExtras().getString("id");
        firstLogin = getIntent().getExtras().getBoolean("FirstLogin");
        if(firstLogin){
            //Toast.makeText(HomepageActivity.this, "first?", Toast.LENGTH_LONG).show();

            availableItems = new ArrayList<String>();

            popReport(); // notify user when he has available item
        }
    }

    private void popReport() {
        //Check match between users items and user's friends' report
        //Find list of items of user
        //Then find list of user's friend and find report of those friends
        //check found items and reports


        //list of user's items
        itemList = getSharedPreferences(ITEMPREFS, 0);
        Set<String> set = itemList.getStringSet(id, new LinkedHashSet<String>());
        ArrayList<String> listOfItems = new ArrayList<String>();
        ArrayList<String> listOfPrices = new ArrayList<String>();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String item = array[0];
            String price = array[1];
            //String location = array[2];
            listOfItems.add(item);
            listOfPrices.add(price);
        }

        //list of user's friends
        friendsList = getSharedPreferences(FRIENDSLIST, 0);
        Set<String> set2 = friendsList.getStringSet(id, new LinkedHashSet<String>());
        ArrayList<String> listOfFriends = new ArrayList<String>();
        /////////ArrayList<String> listOfFriendsId = new ArrayList<String>();

        Object[] arr2 = set2.toArray();
        for(int i=0; i<arr2.length;i++){
            String str = arr2[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String name = array[0];
            //String email = array[1];
            //String tempId = array[4];
            listOfFriends.add(name);
            ///////////listOfFriendsId.add(tempId);
        }
        //Now find reports of these friends
        //Loop through user preference

        reportList = getSharedPreferences(REPORTPREFS, 0);

//PREVIOUS ATTEMPT BEFORE USING PROGRAM MANAGER
        Map<String,?> keys = userList.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            String idKey = entry.getKey();
            String data = entry.getValue().toString();
            String[] array = data.split(Pattern.quote("|"));
            //String userId = array[0];
            //String pass = array[1];
            String storedName = array[2];
            //String email2 = array[3];
            //Toast.makeText(HomepageActivity.this, userId+"|"+storedName, Toast.LENGTH_LONG).show();
            for(int k =0; k<listOfFriends.size(); k++){
                if (storedName.equals(listOfFriends.get(k))){
                    //Toast.makeText(HomepageActivity.this, userId+"|"+storedName, Toast.LENGTH_LONG).show();

                    friendId = idKey;
                    Set<String> set3 = reportList.getStringSet(friendId, new LinkedHashSet<String>());
                    ArrayList<String> listOfReports = new ArrayList<String>();
                    ArrayList<String> listOfReportPrices = new ArrayList<String>();
                    ArrayList<String> listOfReportLocation = new ArrayList<String>();



                    Object[] arr3 = set3.toArray();
                    for(int i=0; i<arr3.length;i++){
                        String str = arr3[i].toString();
                        String[] array2 = str.split(Pattern.quote("|"));
                        String itemName = array2[0];
                        String itemPrice = array2[1];
                        String itemLocation = array2[2];
                        listOfReports.add(itemName);
                        listOfReportPrices.add(itemPrice);
                        listOfReportLocation.add(itemLocation);
                        //check listOfItems,listOfPrices with listOfReports,listOfReportPrices
                        for(int j = 0; j < listOfItems.size(); j++){
                            if(listOfItems.get(j).equals(listOfReports.get(i))){
                                if(Integer.parseInt(listOfPrices.get(j))>=Integer.parseInt(listOfReportPrices.get(i))){
                                    //notify=======================
                                    String message = "Your Item, " + listOfItems.get(j) + " is now available at "+listOfReportLocation.get(i)+ " for " +Integer.parseInt(listOfReportPrices.get(i)) + " dollars";
                                    Toast.makeText(HomepageActivity.this, message, Toast.LENGTH_LONG).show();
                                    String itemToSave = listOfItems.get(j) + "|" + Integer.parseInt(listOfReportPrices.get(i)) +"|" +listOfReportLocation.get(i);
                                    availableItems.add(itemToSave);
                                }
                            }
                        }

                    }
                }
            }

        }
        //PREVIOUS ATTEMPT BEFORE USING PROGRAM MANAGER STOP HERE



    }

    /**
     * logs out the user and navigate to LoginActivity
     * @param view view allows to see logout button
     */
    public void onClick_LogoutButton(View view)
    {
        finish();
    }
    /**
     * navigate to FriendsListActivity with user key
     * @param view view allows to see friend button
     */
    public void onClick_toFriendButton(View view){
        Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        startActivity(i);

    }
    /**
     * navigate to ItemsActivity with user key
     * @param view view allows to see Items button
     */
    public void onClick_toItemButton(View view){
        Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        startActivity(i);

    }

    /**
     * navigate to ReportActivity with user key
     * @param view view allows to see Report button
     */
    public void onClick_toReportButton(View view){
        Intent i = new Intent(getApplicationContext(), ReportActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        startActivity(i);

    }


    /**
     * navigate to AvailableItems Activity with user key
     * @param view view allows to see Report button
     */
    public void onClick_toAvailableItemsButton(View view){
        Intent i = new Intent(getApplicationContext(), AvailableItemsActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        i.putExtra("availableItemsArray",availableItems);
        startActivity(i);

    }


}
