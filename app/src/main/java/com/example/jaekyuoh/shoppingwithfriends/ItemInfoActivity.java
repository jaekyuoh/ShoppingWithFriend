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
 * This class allows to see selected Item's Info
 * @author jaekyuoh / EllisCooper
 */
public class ItemInfoActivity extends ActionBarActivity {
    /**
     * FRIENDSLIST is shared preference file that stores friends' data
     */
    private static final String ITEMPREFS = "itemData";  // this is the friends lists holder
    /**
     * PREFS is shared preference file that stores users' data
     */
    //public static final String PREFS = "credentialPrefs";   // this is user credentials
    private String id;
    private String itemName;
    private SharedPreferences itemsList;
    private SharedPreferences.Editor itemsEditor;
    //private SharedPreferences userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        id = getIntent().getExtras().getString("id");
        itemName = getIntent().getExtras().getString("itemName");

        itemsList = getSharedPreferences(ITEMPREFS,0);
        itemsEditor = itemsList.edit();

        populateItemInfo();
    }

    private void populateItemInfo() {
        itemsList = getSharedPreferences(ITEMPREFS, 0);
        itemsEditor = itemsList.edit();

        Set<String> set = itemsList.getStringSet(id, new LinkedHashSet<String>());
        //Set<String> insertingSet = new LinkedHashSet<String>();
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();

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
                TextView nameText = (TextView)findViewById(R.id.item_name_text);
                TextView emailText = (TextView)findViewById(R.id.price_threshold_text);
                TextView ratingText = (TextView)findViewById(R.id.location_text);
                //TextView reportText = (TextView)findViewById(R.id.friend_report_textview);

                nameText.setText(item1);
                emailText.setText(price1);
                ratingText.setText(location1);
                //reportText.setText(report1);

            }

        }
    }


    /**
     * Delete Item
     * @param view: view allows to see delete button
     */
    public void onClick_deleteItemButton(View view) {
        itemsList = getSharedPreferences(ITEMPREFS, 0);
        itemsEditor = itemsList.edit();

        Set<String> set = itemsList.getStringSet(id, new LinkedHashSet<String>());
        Set<String> insertingSet = new LinkedHashSet<String>();
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        Object[] arr = set.toArray();
        for(int i=0; i<arr.length;i++){
            String str = arr[i].toString();
            String[] array = str.split(Pattern.quote("|"));
            String item1 = array[0];
            String price1 = array[1];
            String location1 = array[2];
            //String report1 = array[3];
            if(!itemName.equals(item1)) {
                insertingSet.add(item1 + "|" + price1+ "|" + location1);
            }
        }
        itemsEditor.putStringSet(id, insertingSet);
        itemsEditor.commit();

        Toast.makeText(this, "Your Item " + itemName + " has been deleted", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
        i.putExtra("id", id);
        finish();
        startActivity(i);
    }

    /**
     * Navigate back to ItemsActivity
     * @param view: view allows to see cancel button
     */
    public void onClick_goBackToItemsButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
        i.putExtra("id", id);
        finish();
        startActivity(i);
    }

    /**
     * Method initiates googleMap display on user's request.
     *
     * @param view: view uses the "Show on Map" button to initiate the map intent.
     */
    public void showMap(View view) {
        TextView locationText = (TextView) findViewById(R.id.location_text);
        String location = locationText.getText().toString();

        Intent showMap = new Intent(getApplicationContext(), MapActivity.class);
        showMap.putExtra("addr", location);
        finish();
        startActivity(showMap);
    }

}
