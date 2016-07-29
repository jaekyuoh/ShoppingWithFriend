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
 * This class allows to see list of Items user have submitted
 * @author jaekyuoh
 */
public class ItemsActivity extends ActionBarActivity {
    private static final String ITEMPREFS = "itemData";
    private String id;
    private SharedPreferences itemList;
    //private SharedPreferences.Editor editor;
    //private Set<String> s;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    //private ArrayList<String> listItems = new ArrayList<String>();
    //private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        populateListView();
        registerClickCallback();
    }


    private void populateListView() {
        id = getIntent().getExtras().getString("id");
        itemList = getSharedPreferences(ITEMPREFS, 0);
        Set<String> set = itemList.getStringSet(id, new LinkedHashSet<String>());
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
        ListView li = (ListView) findViewById(R.id.itemsList);
        li.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.itemsList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long inId) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is string: "
                        + textView.getText().toString();
                Toast.makeText(ItemsActivity.this, message, Toast.LENGTH_LONG).show();


                Intent i = new Intent(ItemsActivity.this, ItemInfoActivity.class);
                //Bundle extras = getIntent().getExtras();
                String itemName = textView.getText().toString(); //item's name
                id = getIntent().getExtras().getString("id");

                i.putExtra("id", id);
                i.putExtra("itemName", itemName);
                finish();
                startActivity(i);
            }
        });
    }

    /**
     * Finish ItemsActivity and navigates to HomepageActivity
     * @param view view allows to see cancel button
     */
    public void onClick_backToHomepageButton(View view) {
        finish();
    }
    /**
     * Navigates to ItemActivity to add Item
     * @param view view allows to see add item button
     */
    public void onClick_addItemButton(View view) {
        Intent i = new Intent(getApplicationContext(), AddItemActivity.class);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        i.putExtra("id", id);
        finish();
        startActivity(i);
    }
}
