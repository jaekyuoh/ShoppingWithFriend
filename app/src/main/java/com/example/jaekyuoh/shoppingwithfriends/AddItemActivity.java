package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class allows to add Item
 * @author jaekyuoh
 */
public class AddItemActivity extends ActionBarActivity {

    public static final String ITEMPREFS = "itemData";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        id = getIntent().getExtras().getString("id");

    }

    /**
     * Add Item
     * @param view view allows to see submit button
     */
    public void onClick_submitItemButton(View view){

        SharedPreferences itemInfo = getSharedPreferences(ITEMPREFS,0);
        //SharedPreferences.Editor editor = itemInfo.edit();
        EditText item = (EditText) findViewById(R.id.item_name_editText);
        EditText priceThreshold = (EditText) findViewById(R.id.price_threshold_editText);
        EditText location = (EditText) findViewById(R.id.location_editText);
        String itemString = item.getText().toString();
        String priceThresholdString = priceThreshold.getText().toString();
        String locationString = location.getText().toString();

        String store = itemString +"|" + priceThresholdString + "|" + locationString;
        String result = ProgramManager.AddItem(itemString,priceThresholdString,locationString,id,store,itemInfo);
        if(result.equals("success")){
            Toast.makeText(getApplicationContext(), "Item has been successfully registered", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
            i.putExtra("id", id);
            finish();
            startActivity(i);
        }
        else if(result.equals("fillOut")){
            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.item_name_editText)).setText("");
            ((EditText) findViewById(R.id.price_threshold_editText)).setText("");
            ((EditText) findViewById(R.id.location_editText)).setText("");
        }
        //PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER
//        if (!(itemString.isEmpty()) && !(priceThresholdString.isEmpty())&& !(locationString.isEmpty())) {
//            Set<String> insertingSet = new LinkedHashSet<String>();
//            Set<String> set = itemInfo.getStringSet(id, new LinkedHashSet<String>());
//            Object[] arr = set.toArray();
//            for (int i = 0; i < arr.length; i++) {
//                String str = arr[i].toString();
//                String[] array = str.split(Pattern.quote("|"));
//                String item1 = array[0];
//                String price1 = array[1];
//                String location1 = array[2];
//                insertingSet.add(item1 + "|" + price1 + "|" + location1);
//            }
//            insertingSet.add(store);
//            editor.putStringSet(id, insertingSet);
//            editor.commit();
//            Toast.makeText(getApplicationContext(), "Item has been successfully registered", Toast.LENGTH_LONG).show();
//            Intent i = new Intent(getApplicationContext(), ItemsActivity.class);
//            i.putExtra("id", id);
//            finish();
//            startActivity(i);
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.item_name_editText)).setText("");
//            ((EditText) findViewById(R.id.price_threshold_editText)).setText("");
//            ((EditText) findViewById(R.id.location_editText)).setText("");
//        }

    }

    /**
     * Navigate back to ItemsActivity
     * @param view view allows to see cancel button
     */
    public void onClick_cancelItemButton(View view){
        finish();
    }


}
