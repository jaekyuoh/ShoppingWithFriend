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
 *this class shows list of items that friend asked for
 * @author Jaekyu Oh
 */

public class RequestedItemsActivity extends ActionBarActivity {
    /**
     * FRIENDSLIST is shared preference file that stores friends' data
     */
    private static final String ITEMPREFS = "itemData";  // this is the friends lists holder
    /**
     * PREFS is shared preference file that stores users' data
     */
    //public static final String PREFS = "credentialPrefs";   // this is user credentials
    private String friendId;
    //private String friendName;
    //private String itemName;
    //private SharedPreferences itemsList;
    //private SharedPreferences.Editor itemsEditor;
    //private SharedPreferences userList;

    //private String id;
    private SharedPreferences itemList;
    //private SharedPreferences.Editor editor;
    //private Set<String> s;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    //private ArrayList<String> listItems = new ArrayList<String>();
    //private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_items);

        //id = getIntent().getExtras().getString("id");
        friendId = getIntent().getExtras().getString("friendId");
        //friendName = getIntent().getExtras().getString("friendName");
        //itemName = getIntent().getExtras().getString("itemName");


        //itemsList = getSharedPreferences(ITEMPREFS,0);
        //itemsEditor = itemsList.edit();

        populateListView();
        registerClickCallback();
    }

    private void populateListView() {
        //id = getIntent().getExtras().getString("id");
        friendId = getIntent().getExtras().getString("friendId");
        itemList = getSharedPreferences(ITEMPREFS, 0);
        Set<String> set = itemList.getStringSet(friendId, new LinkedHashSet<String>());
        ArrayList<String> listOfItems = new ArrayList<String>();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String item = array[0];
            //String price = array[1];
            //String location = array[2];
            listOfItems.add(item);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.items_populate, listOfItems);
        ListView li = (ListView) findViewById(R.id.requestedItemsList);
        Toast.makeText(RequestedItemsActivity.this, "This is "+friendId +"'s Item ", Toast.LENGTH_LONG).show();
        li.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.requestedItemsList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long inId) {
                TextView textView = (TextView) viewClicked;
//                String message = "You clicked # " + position + ", which is string: "
//                        + textView.getText().toString();
//                Toast.makeText(RequestedItemsActivity.this, message, Toast.LENGTH_LONG).show();


                Intent i = new Intent(RequestedItemsActivity.this, FriendItemInfoActivity.class);
                //Bundle extras = getIntent().getExtras();
                String itemName = textView.getText().toString(); //item's name
                //friendId = getIntent().getExtras().getString("firendId");

                i.putExtra("friendId", friendId);
                i.putExtra("itemName", itemName);
                finish();
                startActivity(i);
            }
        });
    }


//    friendsList = getSharedPreferences(FRIENDSLIST, 0);
//    friendsEditor = friendsList.edit();
//
//    Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
//    Set<String> insertingSet = new LinkedHashSet<String>();
//    //Toast.makeText(this, id, Toast.LENGTH_LONG).show();
//
//    Object[] arr = set.toArray();
//    for(int i=0; i<arr.length;i++){
//        String str = arr[i].toString();
//        String[] array = str.split(Pattern.quote("|"));
//        String name1 = array[0];
//        String email1 = array[1];
//        String rating1 = array[2];
//        String report1 = array[3];
//        String friendId = array[4];//
//        if(!friendName.equals(name1)) {
//            insertingSet.add(name1 + "|" + email1+ "|" + rating1+ "|" + report1 + "|" + friendId);
//        }
//    }
//    friendsEditor.putStringSet(id, insertingSet);
//    friendsEditor.commit();

    /**
     * go back to friend list activity
     * @param view: view allows to see cancel button
     */
    public void onClick_backToFriendInfoButton(View view)
    {
        //Intent i = new Intent(getApplicationContext(), FriendInfoActivity.class);
        //i.putExtra("id", id);
        finish();
        //startActivity(i);
    }


}
