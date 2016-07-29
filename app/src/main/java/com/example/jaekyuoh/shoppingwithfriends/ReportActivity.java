package com.example.jaekyuoh.shoppingwithfriends;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *This activity allows user to report item.
 * It contains Report Button and Cancel Button.
 * @author Jaekyu Oh
 */
public class ReportActivity extends ActionBarActivity {

    private static final String PREFS = "credentialPrefs";
    private static final String REPORTPREFS = "reportData";  // this is the report holder
    public static final String FRIENDSLIST = "friendsData";  // this is the friends lists holder
    //private SharedPreferences.Editor friendsEditor;


    private String id;
    //private Set<String> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        id = getIntent().getExtras().getString("id");

    }


    /**
     * Add Report
     * @param view view allows to see Report button
     */
    public void onClick_addReportButton(View view){

        SharedPreferences reportInfo = getSharedPreferences(REPORTPREFS,0);
        SharedPreferences.Editor editor = reportInfo.edit();
        EditText item = (EditText) findViewById(R.id.reportItemName);
        EditText priceThreshold = (EditText) findViewById(R.id.reportPrice);
        EditText location = (EditText) findViewById(R.id.reportLocation);

        String itemString = item.getText().toString();
        String priceThresholdString = priceThreshold.getText().toString();
        String locationString = location.getText().toString();

        String store = itemString +"|" + priceThresholdString + "|" + locationString;
        if ((itemString.isEmpty()) || (priceThresholdString.isEmpty())|| (locationString.isEmpty())){
            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.item_name_editText)).setText("");
            ((EditText) findViewById(R.id.price_threshold_editText)).setText("");
            ((EditText) findViewById(R.id.location_editText)).setText("");
            finish();
        }
        else if (!(itemString.isEmpty()) && !(priceThresholdString.isEmpty())&& !(locationString.isEmpty())) {
        //if (!(itemString.isEmpty()) && !(priceThresholdString.isEmpty())&& !(locationString.isEmpty())) {
        //if ((itemString!="") && (priceThresholdString!="")&& (locationString!="")) {
            Set<String> insertingSet = new LinkedHashSet<String>();
            Set<String> set = reportInfo.getStringSet(id, new LinkedHashSet<String>());
            Object[] arr = set.toArray();
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i].toString();
                String[] array = str.split(Pattern.quote("|"));
                String item1 = array[0];
                String price1 = array[1];
                String location1 = array[2];
                insertingSet.add(item1 + "|" + price1 + "|" + location1);
            }
            insertingSet.add(store);
            editor.putStringSet(id, insertingSet);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Report has been successfully submitted", Toast.LENGTH_LONG).show();
            //Intent i = new Intent(getApplicationContext(), HomepageActivity.class);

            updateCredential();
            //NEED TO ADD # of REPORTS to CREDENTIALS


            updateFriendList();
            //NEED TO UPDATE # of REPORTS to Friends

//            friends = friendsList.getStringSet(id, new LinkedHashSet<String>()); //the friends list
            //String[] array2 = info.split(Pattern.quote("|"));

//            Set<String> insertingSet2 = new LinkedHashSet<String>();
//            Set<String> set2 = friendsList.getStringSet(id, new LinkedHashSet<String>());
//            Object[] arr2 = set2.toArray();
//            for(int k=0; k<arr2.length;k++){
//                String str = arr2[k].toString();
//                String[] array2 = str.split(Pattern.quote("|"));
//                String name1 = array2[0];
//                String email1 = array2[1];
//                String friendId1 = array2[4];
//                String reportStr = array2[3];
//                int reportNum = Integer.parseInt(reportStr);
//                int newNum = reportNum + 1;
//                insertingSet2.add(name1+"|"+email1+"|"+"0"+"|"+ newNum + "|"+ friendId1);
//
//            }
//            insertingSet2.add(store);
//            friendsEditor.putStringSet(id, insertingSet);
//            friendsEditor.commit();


            //i.putExtra("id", id);
            finish();
            //startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.item_name_editText)).setText("");
            ((EditText) findViewById(R.id.price_threshold_editText)).setText("");
            ((EditText) findViewById(R.id.location_editText)).setText("");
        }

    }

    private void updateFriendList() {
        SharedPreferences friendsList = getSharedPreferences(FRIENDSLIST,0);
        //ArrayList<String> result = ProgramManager.updateFriend(friendsList, id);

        //PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER
        SharedPreferences.Editor friendsEditor = friendsList.edit();
        Map<String,?> keys = friendsList.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()) {
            String idKey = entry.getKey();
            //Set<String> tempSet = new LinkedHashSet<String>();
            //String[] strl = entry.getValue();
            Set<Object> temp = (Set<Object>) entry.getValue();
            Object[] tempArray = (Object[])temp.toArray();
            for (int ind = 0; ind < tempArray.length; ind++){
                String d = tempArray[ind].toString();
                String[] array2 = d.split(Pattern.quote("|"));
                String name2 = array2[0];
                String email2 = array2[1];
                //String rate2 = array2[2];
                String report2 = array2[3];
                String friendId2 = array2[4];
                //Toast.makeText(getApplicationContext(), "SharedPreferences: "+ entry.getKey() + ":" + entry.getValue(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), d, Toast.LENGTH_LONG).show();

                //Log.v("SharedPreferences", entry.getKey() + ":" + entry.getValue().toString());
                //Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();

                if(friendId2.equals(id)){
                    //when this user is in someone's friend list
                    //Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();

                    int reportNum = Integer.parseInt(report2);
                    int newNum = reportNum + 1;
                    //String newValue = name2+"|"+email2+"|"+"0"+"|"+ newNum + "|"+ friendId2;

                    //STORE newValue........
                    Set<String> insertingSet2 = new LinkedHashSet<String>();
                    Set<String> set2 = friendsList.getStringSet(idKey, new LinkedHashSet<String>());
                    Object[] arr2 = set2.toArray();
                    for(int k=0; k<arr2.length;k++) {
                        String str2 = arr2[k].toString();
                        String[] array3 = str2.split(Pattern.quote("|"));
                        String name1 = array3[0];
                        String email1 = array3[1];
                        String rate1 = array3[2];
                        String reportStr = array3[3];
                        String friendId1 = array3[4];
                        if(friendId1.equals(id)){
                            int reportNum3 = Integer.parseInt(reportStr);
                            int newNum3 = reportNum3 + 1;
                            insertingSet2.add(name1 + "|" + email1 + "|" + rate1 + "|" + newNum3 + "|" + friendId1);
                            Toast.makeText(getApplicationContext(), "Updated " + idKey +"'s friendList!!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            insertingSet2.add(name1 + "|" + email1 + "|" + rate1 + "|" + reportStr + "|" + friendId1);
                        }

                    }
                    friendsEditor.putStringSet(idKey, insertingSet2);
                    friendsEditor.commit();
                    //insertingSet2.add(store);
                    //entry.setValue(idKey,newValue);
                }
            }
            //String data2 = entry.getValue().toString();
            //String[] array2 = data2.split(Pattern.quote("|"));
//            String name2 = array2[0];
//            String email2 = array2[1];
//            String rate2 = array2[2];
//            String report2 = array2[3];
//            String friendId2 = array2[4];
//            Toast.makeText(getApplicationContext(), "SharedPreferences: "+ entry.getKey() + ":" + entry.getValue(), Toast.LENGTH_LONG).show();
//
//            //Log.v("SharedPreferences", entry.getKey() + ":" + entry.getValue().toString());
//            //Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();
//
//            if(friendId2.equals(id)){
//                //when this user is in someone's friend list
//                Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();
//
//                int reportNum = Integer.parseInt(report2);
//                int newNum = reportNum + 1;
//                String newValue = name2+"|"+email2+"|"+"0"+"|"+ newNum + "|"+ friendId2;
//
//                //STORE newValue........
//                Set<String> insertingSet2 = new LinkedHashSet<String>();
//                Set<String> set2 = friendsList.getStringSet(idKey, new LinkedHashSet<String>());
//                Object[] arr2 = set2.toArray();
//                for(int k=0; k<arr2.length;k++) {
//                    String str2 = arr2[k].toString();
//                    String[] array3 = str2.split(Pattern.quote("|"));
//                    String name1 = array3[0];
//                    String email1 = array3[1];
//                    String rate1 = array3[2];
//                    String reportStr = array3[3];
//                    String friendId1 = array3[4];
//                    if(friendId1.equals(id)){
//                        int reportNum3 = Integer.parseInt(reportStr);
//                        int newNum3 = reportNum3 + 1;
//                        insertingSet2.add(name1 + "|" + email1 + "|" + rate1 + "|" + newNum3 + "|" + friendId1);
//                        Toast.makeText(getApplicationContext(), "Updated " + idKey +"'s friendList!!", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        insertingSet2.add(name1 + "|" + email1 + "|" + rate1 + "|" + reportStr + "|" + friendId1);
//                    }
//
//                }
//                friendsEditor.putStringSet(idKey, insertingSet2);
//                friendsEditor.commit();
//                //insertingSet2.add(store);
//                //entry.setValue(idKey,newValue);
//            }
        }
        //PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER STOP HERE
    }

    private void updateCredential() {
        //NEED TO ADD # of REPORTS to CREDENTIALS
        SharedPreferences login = getSharedPreferences(PREFS,0);
        SharedPreferences.Editor loginEditor = login.edit();
        String data = login.getString(id, null);
        String[] array = data.split(Pattern.quote("|"));
        String userId = array[0];
        String pass = array[1];
        String name = array[2];
        String email = array[3];
        String report = array[4];

        int oldReportNum = Integer.parseInt(report);
        int newReportNum = oldReportNum + 1;
        String newReport = ""+newReportNum;

        String newStore = userId+"|"+pass+"|"+name + "|" + email + "|" + newReport;
        loginEditor.putString(id, newStore);
        loginEditor.commit();
    }

    /**
     * navigate to HomepageActivity with user key
     * @param view view allows to see Cancel button
     */
    public void onClick_toHomepageFromReportButton(View view){
        //Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
        //Bundle extras = getIntent().getExtras();
        //String id = extras.getString("id");
        //i.putExtra("id", id);
        finish();
        //startActivity(i);

    }



}
