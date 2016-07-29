package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *this class shows Info of selected friend's info of items or sales
 * @author Jaekyu Oh
 */
public class FriendItemInfoActivity extends ActionBarActivity {
    /**
     * FRIENDSLIST is shared preference file that stores friends' data
     */
    private static final String ITEMPREFS = "itemData";  // this is the friends lists holder
    /**
     * PREFS is shared preference file that stores users' data
     */
    //public static final String PREFS = "credentialPrefs";   // this is user credentials
    private String friendId;
    private String id;
    private String itemName;
    private SharedPreferences itemsList;
    //private SharedPreferences userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_info);

        id = getIntent().getExtras().getString("id");

        friendId = getIntent().getExtras().getString("friendId");
        itemName = getIntent().getExtras().getString("itemName");

        itemsList = getSharedPreferences(ITEMPREFS, 0);

        populateItemInfo();
    }
    private void populateItemInfo() {
        itemsList = getSharedPreferences(ITEMPREFS, 0);

        Set<String> set = itemsList.getStringSet(friendId, new LinkedHashSet<String>());

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String item1 = array[0];
            String price1 = array[1];
            String location1 = array[2];
            //String report1 = array[3];
            if(itemName.equals(item1)){
                //assign name, email, rating, report to text views
                TextView nameText = (TextView)findViewById(R.id.item_name_text2);
                TextView emailText = (TextView)findViewById(R.id.price_threshold_text2);
                TextView ratingText = (TextView)findViewById(R.id.location_text2);
                //TextView reportText = (TextView)findViewById(R.id.friend_report_textview);

                nameText.setText(item1);
                emailText.setText(price1);
                ratingText.setText(location1);
                //reportText.setText(report1);

            }

        }
    }

    /**
     * Navigate back to RequestedItemsActivity
     * @param view: view allows to see cancel button
     */
    public void onClick_backToRequestedItemsButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), RequestedItemsActivity.class);
        i.putExtra("id", id);
        i.putExtra("friendId", friendId);
        i.putExtra("itemName", itemName);
        finish();
        startActivity(i);
        //friendName = getIntent().getExtras().getString("friendName");

    }



}
