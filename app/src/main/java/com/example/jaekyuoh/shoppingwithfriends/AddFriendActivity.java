package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 *this class allows to add friends
 * @author Jaekyu Oh
 */
public class AddFriendActivity extends ActionBarActivity {

    private static final String FRIENDSLIST = "friendsData";  // this is the friends lists holder
    private static final String PREFS = "credentialPrefs";   // this is user credentials
    private String id;
    private SharedPreferences friendsList;
    //private SharedPreferences.Editor friendsEditor;
    private SharedPreferences userList;
    //private SharedPreferences.Editor userEditor;
    //private Set<String> friends;
    private String users;
    //private boolean exist;
    //private String friendId;


    //NEW
//    public static final String FRIENDPREF = "newFriendsData";  // this is the friends lists holder
//    private SharedPreferences friendPref;
//    private SharedPreferences.Editor friendPrefEditor;
//    private Set<String> friends1;
//
//    public static final String USERPREF = "newCredentials";
//    SharedPreferences userPref;
//    SharedPreferences.Editor userPrefEditor;
//    private String users1;
//    private User userObj;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        id = getIntent().getExtras().getString("id");
        friendsList = getSharedPreferences(FRIENDSLIST,0);
        //friendsEditor = friendsList.edit();
        userList = getSharedPreferences(PREFS,0);
        //userEditor = userList.edit();
        //friends = friendsList.getStringSet(id, new LinkedHashSet<String>()); //the friends list
        users = userList.getString(id, null); // this is here just to check to see if the key (email) exists


        ///NEW ATTEMPT
//        friendPref = getSharedPreferences(FRIENDPREF,MODE_PRIVATE);
//        friendPrefEditor = friendPref.edit();
//        //NEW
//        Gson friendGson = new Gson();
//        Set<String> friendJson = friendPref.getStringSet(id, new LinkedHashSet<String>());
//        Object[] newArr = friendJson.toArray();
//        //Friend friendObj = friendGson.fromJson(newArr[0].toString(), Friend.class); // not sure whether it works.....
//        //
//
//        userPref = getSharedPreferences(USERPREF,MODE_PRIVATE);
//        userPrefEditor = userPref.edit();
//
//        Gson gson = new Gson();
//        String json = userPref.getString(id, null);
//        userObj = gson.fromJson(json, User.class);
//
//
//        Map<String,?> userKeys = userPref.getAll();
//        for(Map.Entry<String,?> entry : userKeys.entrySet()){
//            String idKey = entry.getKey();
//            Gson gson2 = new Gson();
//            String s = entry.getValue().toString();
//            User data = gson2.fromJson(s, User.class);
//            String userId = data.getId().toString();
//            String userPass = data.getPassword().toString();
//            String userName = data.getName().toString();
//            String userEmail = data.getEmail().toString();
//            String userNumReport = data.getNumReport().toString();
//            Toast.makeText(getApplicationContext(), userId,
//                    Toast.LENGTH_LONG).show();
//
//        }

        ///
    }

    /**
     * add friend by typing name and email
     * @param view view allows to see add button
     */
    public void onClick_AddButton(View view)
    {
        //PREVIOUS ATTEMPT
        EditText name = (EditText) findViewById(R.id.friend_user_name);
        EditText email = (EditText) findViewById(R.id.user_email);
        String nameString = name.getText().toString();
        String emailString = email.getText().toString();

////////getting # of reports from user credentials
        String[] temparray = users.split(Pattern.quote("|"));
        String report = temparray[4];

        ///NEW
//        String newReport = userObj.getNumReport();
//        Friend addingFriend;
//        String initialRating = "0";
        ///

        String store = nameString+"|"+emailString+"|"+"0"+"|"+report; //last two is for default rating and #of report

        //Loop through user preference
       // Map<String,?> keys = userList.getAll();
        //exist = false;

//        Map<String,?> userKeys = userPref.getAll();
//        for(Map.Entry<String,?> entry : userKeys.entrySet()){
//            String idKey = entry.getKey();
//            Gson gson = new Gson();
//            String s = entry.getValue().toString();
//            User data = gson.fromJson(s, User.class);
//            String userId = data.getId().toString();
//            String userPass = data.getPassword().toString();
//            String userName = data.getName().toString();
//            String userEmail = data.getEmail().toString();
//            String userNumReport = data.getNumReport().toString();
//            Toast.makeText(getApplicationContext(), userId + userPass + userName + userEmail + userNumReport,
//                    Toast.LENGTH_LONG).show();
////            Toast.makeText(getApplicationContext(), s,
////                            Toast.LENGTH_LONG).show();
//
//
//
//        }

        String result = ProgramManager.AddFriend(nameString,emailString,id,store,userList,friendsList);
        if(result.equals("fillOut1")){
            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.friend_user_name)).setText("");
            ((EditText) findViewById(R.id.user_email)).setText("");
        }
        else if(result.equals("alreadyInFriendList")){
            Toast.makeText(getApplicationContext(), "User is already your friend." + store,
                    Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.friend_user_name)).setText("");
            ((EditText) findViewById(R.id.user_email)).setText("");
        }
        else if(result.equals("success")){
            Toast.makeText(getApplicationContext(), "Successfully registered!",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
            i.putExtra("id", id);
            finish();
            startActivity(i);
        }
        else if (result.equals("fillOut2")){
            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.friend_user_name)).setText("");
            ((EditText) findViewById(R.id.user_email)).setText("");
        }
        else if(result.equals("friendIsNotUser")){
            Toast.makeText(getApplicationContext(), "Your Friend is not a user", Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.friend_user_name)).setText("");
            ((EditText) findViewById(R.id.user_email)).setText("");
        }
//PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER
//        for(Map.Entry<String,?> entry : keys.entrySet()){
//            String idKey = entry.getKey();
//            String data = entry.getValue().toString();
//            String[] array = data.split(Pattern.quote("|"));
//            String userId = array[0];
//            String pass = array[1];
//            String storedName = array[2];
//            String email2 = array[3];
//            if (storedName.equals(nameString)){
//                exist = true;
//                friendId = idKey;
//                store = store + "|" + friendId;
//                //addingFriend = new Friend(friendId,nameString,emailString,initialRating,newReport);
//                //break;
//
//            }
//        }
//        if ((nameString.isEmpty()) && (emailString.isEmpty())) {
//            Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.friend_user_name)).setText("");
//            ((EditText) findViewById(R.id.user_email)).setText("");
//        }
//        else if(exist) {
//            if (!(nameString.isEmpty()) && !(emailString.isEmpty())) {
//                if (friends.contains(store)) {
//                    Toast.makeText(getApplicationContext(), "User is already your friend." + store,
//                            Toast.LENGTH_LONG).show();
//                    ((EditText) findViewById(R.id.friend_user_name)).setText("");
//                    ((EditText) findViewById(R.id.user_email)).setText("");
//                } else {
//                    ///////
//                    Set<String> insertingSet = new LinkedHashSet<String>();
//                    Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
//                    Object[] arr = set.toArray();
//                    for(int i=0; i<arr.length;i++){
//                        String str = arr[i].toString();
//                        String[] array = str.split(Pattern.quote("|"));
//                        String name1 = array[0];
//                        String email1 = array[1];
//                        String friendId1 = array[4];
//                        insertingSet.add(name1+"|"+email1+"|"+"0"+"|"+"0"  + "|"+ friendId1);
//                    }
//                    insertingSet.add(store);
//                    friendsEditor.putStringSet(id, insertingSet);
//                    friendsEditor.commit();
//
//                    Toast.makeText(getApplicationContext(), "Successfully registered!",
//                            Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
//                    i.putExtra("id", id);
//                    finish();
//                    startActivity(i);
//                }
//            } else {
//                Toast.makeText(getApplicationContext(), "Fill out entire form", Toast.LENGTH_LONG).show();
//                ((EditText) findViewById(R.id.friend_user_name)).setText("");
//                ((EditText) findViewById(R.id.user_email)).setText("");
//            }
//        }
//        else if(!exist){
//            Toast.makeText(getApplicationContext(), "Your Friend is not a user", Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.friend_user_name)).setText("");
//            ((EditText) findViewById(R.id.user_email)).setText("");
//        }

        //PREVIOUS ATTEMPT STOPS HERE

    }
    /**
     * navigate back to FriendListActivity
     * @param view view allows to see cancel button
     */
    public void onClick_toHomeButton(View view)
    {
        //Intent i = new Intent(getApplicationContext(), FriendsListActivity.class);
        //Bundle extras = getIntent().getExtras();
        //String id = extras.getString("id");
        //i.putExtra("id", id);
        //startActivity(i);


        //Finish AddFriendActivity
        finish();
    }

}
