package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *this class shows Available Items
 * @author Jaekyu Oh
 */
public class AvailableItemsActivity extends ActionBarActivity {
    private ArrayList<String> availableItems;
    //private String store = "";
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_items);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        availableItems = extras.getStringArrayList("availableItemsArray");

        //populateItems();
        populateListView();
        registerClickCallback();
    }

    private void populateListView() {
        ArrayList<String> listOfItems = new ArrayList<String>();
        for(int i =0; i<availableItems.size();i++) {
            String data = availableItems.get(i);
            String[] array = data.split(Pattern.quote("|"));
            String itemName = array[0];
            //String itemPrice = array[1];
            //String itemLocation = array[2];
            listOfItems.add(itemName);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.items_populate, listOfItems);
        ListView li = (ListView) findViewById(R.id.availableItemsListView);
        li.setAdapter(adapter);

    }
    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.availableItemsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long inId) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is string: "
                        + textView.getText().toString();
                Toast.makeText(AvailableItemsActivity.this, message, Toast.LENGTH_LONG).show();


                Intent i = new Intent(AvailableItemsActivity.this, AvailableItemInfoActivity.class);
                //Bundle extras = getIntent().getExtras();
                String itemName = textView.getText().toString(); //item's name
                id = getIntent().getExtras().getString("id");

                i.putExtra("id", id);
                i.putExtra("itemName", itemName); //available Item Name
                i.putExtra("availableItemsArray",availableItems); // itemName will be compared with this in next activity
                //i.putExtra("itemPrice",itemPrice);
                //i.putExtra("itemLocation", itemLocation);
                //i.putExtra("itemPrice", itemPrice);
                finish();
                startActivity(i);
            }
        });
    }

//    private void populateItems() {
//
//        for(int i =0; i<availableItems.size();i++){
//            String data = availableItems.get(i);
//            String[] array = data.split(Pattern.quote("|"));
//            String itemName = array[0];
//            String itemPrice = array[1];
//            String itemLocation = array[2];
////            String temp = itemName + " is currently available at " + itemLocation + " for " + itemPrice + " dollars.";
////            store = store  + temp + "\n";
//
//        }
////        TextView emptyText = (TextView)findViewById(R.id.available_items_text);
////        emptyText.setText(store);
//
//
//    }
    /**
     *navigate to Homepage
     * @param view this allows to see cancel button
     */
    public void onClick_backToHomepageButton(View view)
    {
        finish();
    }

}
