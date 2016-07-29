package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import java.util.Set;
import java.util.regex.Pattern;

/**
 *this class shows list of user's friends
 * @author Jaekyu Oh
 */
public class FriendsListActivity extends ActionBarActivity {

    private static final String FRIENDSLIST = "friendsData";
    private String id;
    private SharedPreferences friendsList;
    //private SharedPreferences.Editor editor;
    //private Set<String> s;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    //private ArrayList<String> listItems = new ArrayList<String>();
    //private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        
        populateListView();
        registerClickCallback();

    }

    private void populateListView() {
        id = getIntent().getExtras().getString("id");
        friendsList = getSharedPreferences(FRIENDSLIST, 0);
        Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
        ArrayList<String> listOfFriends = new ArrayList<String>();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String name = array[0];
            //String email = array[1];
            listOfFriends.add(name);
        }


//        String str = friendsList.getString(id, null);//
//
//        if (str.isEmpty()){
//            Toast.makeText(getApplicationContext(), "You have no friends",
//                    Toast.LENGTH_LONG).show();
//        }
//        else if(!str.isEmpty()){
//            String[] array = str.split(Pattern.quote("|"));
//            String name = array[0];
//            String email = array[1];
//            listItems.add(name);
//            listItems.add(email);
//
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.friends_populate, listOfFriends);
        ListView li = (ListView) findViewById(R.id.list);
        li.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long inId) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is string: "
                        + textView.getText().toString();
                Toast.makeText(FriendsListActivity.this, message, Toast.LENGTH_LONG).show();


                Intent i = new Intent(FriendsListActivity.this, FriendInfoActivity.class);
                //Bundle extras = getIntent().getExtras();
                String friendName = textView.getText().toString(); //friend's name
                id = getIntent().getExtras().getString("id");

                i.putExtra("id", id);
                i.putExtra("FriendName", friendName);
                finish();
                startActivity(i);
            }
        });
    }

    /**
     * navigate to HomepageActivity
     * @param view view allows to see cancel button
     */
    public void onClick_backToHomeButton(View view) {
        //Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
        //startActivity(i);

        //Finish FriendsListActivity
        finish();
    }
    /**
     * navigate to AddFriendActivity with user key to add friend
     * @param view view allows to see add friend button
     */
    public void onClick_addFriendButton(View view) {
        Intent i = new Intent(getApplicationContext(), AddFriendActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        finish();
        startActivity(i);
    }

//    public void onClick_deleteFriendButton(View view) {
//        Intent i = new Intent(getApplicationContext(), DeleteFriendActivity.class);
//        Bundle extras = getIntent().getExtras();
//        String id = extras.getString("id");
//        i.putExtra("id", id);
//        startActivity(i);
//    }
}