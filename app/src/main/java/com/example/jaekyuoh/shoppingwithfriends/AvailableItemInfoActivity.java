package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class AvailableItemInfoActivity extends ActionBarActivity {
    private ArrayList<String> availableItems;
    //private String store = "";
    //private String id;
    private String availItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_item_info);

        Bundle extras = getIntent().getExtras();
        //id = extras.getString("id");
        availItem = extras.getString("itemName");
        availableItems = extras.getStringArrayList("availableItemsArray");

        populateItems();
    }


        private void populateItems() {

        for(int i = 0; i < availableItems.size(); i++){
            String data = availableItems.get(i);
            String[] array = data.split(Pattern.quote("|"));
            String itemName = array[0];
            String itemPrice = array[1];
            String itemLocation = array[2];
            if(itemName.equals(availItem)){
                //assign name, email, rating, report to text views
                TextView nameText = (TextView)findViewById(R.id.availItemNameTextView);
                TextView priceText = (TextView)findViewById(R.id.availItemPriceTextView);
                TextView locationText = (TextView)findViewById(R.id.availItemLocationTextView);
                //TextView reportText = (TextView)findViewById(R.id.friend_report_textview);

                nameText.setText(itemName);
                priceText.setText(itemPrice);
                locationText.setText(itemLocation);
            }
//            String temp = itemName + " is currently available at " + itemLocation + " for " + itemPrice + " dollars.";
//            store = store  + temp + "\n";

        }
//        TextView emptyText = (TextView)findViewById(R.id.available_items_text);
//        emptyText.setText(store);


    }
    /**
     * Navigate back to AvailableItemsActivity
     * @param view: view allows to see cancel button
     */
    public void onClick_goBackToAvailableItemsButton(View view)
    {
        //Intent i = new Intent(getApplicationContext(), AvailableItemsActivity.class);
        //i.putExtra("id", id);
        finish();
        //startActivity(i);
    }

    /**
     * Method shows the google map location of an item on the user's request.
     *
     * @param view: allows the "Show on Map" button to initiate the map intent.
     */
    public void showOnMap(View view) {
        TextView locationText = (TextView) findViewById(R.id.availItemLocationTextView);
        String location = locationText.getText().toString();

        Intent showOnMap = new Intent(getApplicationContext(), MapActivity.class);
        showOnMap.putExtra("addr", location);
        finish();
        startActivity(showOnMap);
    }

}
