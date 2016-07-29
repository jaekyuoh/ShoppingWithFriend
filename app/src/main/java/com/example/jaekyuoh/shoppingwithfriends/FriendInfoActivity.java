package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *this class shows Info of user's friend
 * @author Jaekyu Oh
 */
public class FriendInfoActivity extends ActionBarActivity {
    /**
     * FRIENDSLIST is shared preference file that stores friends' data
     */
    private static final String FRIENDSLIST = "friendsData";  // this is the friends lists holder
    /**
     * PREFS is shared preference file that stores users' data
     */
    //public static final String PREFS = "credentialPrefs";   // this is user credentials
    private String id;
    private String friendName;
    private String friendId;
    private SharedPreferences friendsList;
    private SharedPreferences.Editor friendsEditor;
    //private SharedPreferences userList;
    //private SharedPreferences.Editor userEditor;
    //private Set<String> friends;
    //private String users;
    //private boolean exist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);

        id = getIntent().getExtras().getString("id");
        friendName = getIntent().getExtras().getString("FriendName");

        friendsList = getSharedPreferences(FRIENDSLIST,0);
        friendsEditor = friendsList.edit();
        //userList = getSharedPreferences(PREFS,0);
        //userEditor = userList.edit();
        //friends = friendsList.getStringSet(id, new LinkedHashSet<String>()); //the friends list
        //users = userList.getString(id, null); // this is here just to check to see if the key (email) exists

        populateFriendInfo();
    }

    private void populateFriendInfo() {
        //id = getIntent().getExtras().getString("id");
        friendsList = getSharedPreferences(FRIENDSLIST, 0);
        friendsEditor = friendsList.edit();

        Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
        //Set<String> insertingSet = new LinkedHashSet<String>();
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String name1 = array[0];
            String email1 = array[1];
            String rating1 = array[2];
            String report1 = array[3];
            String tempId = array[4];
            if(friendName.equals(name1)){
                //assign name, email, rating, report to text views
                TextView nameText = (TextView)findViewById(R.id.friend_name_textview);
                TextView emailText = (TextView)findViewById(R.id.friend_email_textview);
                TextView ratingText = (TextView)findViewById(R.id.friend_rating_textview);
                TextView reportText = (TextView)findViewById(R.id.friend_report_textview);
                friendId =tempId;
                nameText.setText(name1);
                emailText.setText(email1);
                ratingText.setText(rating1);
                reportText.setText(report1);

            }

        }
    }


    /**
     * Delete friend
     * @param view: view allows to see delete button
     */
    public void onClick_deleteFriend(View view) {
        friendsList = getSharedPreferences(FRIENDSLIST, 0);
        friendsEditor = friendsList.edit();

        Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
        Set<String> insertingSet = new LinkedHashSet<String>();
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String name1 = array[0];
            String email1 = array[1];
            String rating1 = array[2];
            String report1 = array[3];
            String friendId = array[4];//
            if(!friendName.equals(name1)) {
                insertingSet.add(name1 + "|" + email1+ "|" + rating1+ "|" + report1 + "|" + friendId);
            }
        }
        friendsEditor.putStringSet(id, insertingSet);
        friendsEditor.commit();

        Toast.makeText(this, "Your Friend "+ friendName + " has been deleted", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
        i.putExtra("id", id);
        finish();//Maybe??
        startActivity(i);
    }

    /**
     * go back to friend list activity
     * @param view: view allows to see cancel button
     */
    public void onClick_goBackToFriendsListButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
        i.putExtra("id", id);
        finish();
        startActivity(i);
    }


    public void onClick_goToRequestedItemButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), RequestedItemsActivity.class);
        i.putExtra("id", id);
        i.putExtra("friendName", friendName);
        i.putExtra("friendId", friendId);
        startActivity(i);
    }


}
