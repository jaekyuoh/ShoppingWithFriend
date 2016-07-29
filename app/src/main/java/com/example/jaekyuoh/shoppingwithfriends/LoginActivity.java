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
 *this class represents login page and process login procedure
 * @author Jaekyu Oh
 */

public class LoginActivity extends ActionBarActivity {

    /** this variable is for shared preference file name*/
    private static final String PREFS = "credentialPrefs";
    //NEW
    //public static final String USERPREF = "newCredentials";
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * checks whether it is valid user or not by checking shared preference file.
     * If not a valid user, show message that he is not.
     * If it is valid user, direct to Homepage
     * @param view this allows to see login button
     */
     public void onClick_loginButton(View view)
    {
        SharedPreferences login = getSharedPreferences(PREFS,0);

        ///NEW
//        SharedPreferences userPref = getSharedPreferences(USERPREF,MODE_PRIVATE);
//        SharedPreferences.Editor userPrefEditor = userPref.edit();
        ///

        //SharedPreferences.Editor editor = login.edit();
        //Set<String> s =  new HashSet<String>();
        //Set<String> s = new LinkedHashSet<String>();
        //s = login.getStringSet();



        EditText id = (EditText) findViewById(R.id.idField);
        EditText password = (EditText) findViewById(R.id.passwordField);
        String idString = id.getText().toString();
        String passwordString = password.getText().toString();

//        Set<String> s = login.getStringSet(idString, new LinkedHashSet<String>());

        //NEW
//        Gson gson = new Gson();
//        String json = userPref.getString(idString, null);
//        User userObj = gson.fromJson(json, User.class);
        //
//OLD
        String data = login.getString(idString, null);
        String result = ProgramManager.ValidateUser(idString,passwordString,data);
        if(result.equals("fillOut")){
            Toast.makeText(getApplicationContext(), "Type your username and password",
                    Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.idField)).setText("");
            ((EditText) findViewById(R.id.passwordField)).setText("");
        }
        else if (result.equals("notRegistered")){
            Toast.makeText(getApplicationContext(), "You are not registered yet",
                    Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.idField)).setText("");
            ((EditText) findViewById(R.id.passwordField)).setText("");
        }
        else if (result.equals("invalidAccount")){
            Toast.makeText(getApplicationContext(), "Invalid account, Try again ",
                    Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.idField)).setText("");
            ((EditText) findViewById(R.id.passwordField)).setText("");
        }
        else if (result.equals("login")){
            String[] array = data.split(Pattern.quote("|"));
            String userId = array[0];
            //String pass = array[1];
            String name = array[2];

            Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
            i.putExtra("id",userId);
            ((EditText) findViewById(R.id.idField)).setText("");
            ((EditText) findViewById(R.id.passwordField)).setText("");
            Boolean firstLogin = true;
            i.putExtra("FirstLogin",firstLogin);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Welcome "+name,Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid account, Try again",
                    Toast.LENGTH_LONG).show();
            ((EditText) findViewById(R.id.idField)).setText("");
            ((EditText) findViewById(R.id.passwordField)).setText("");

        }
        //PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER
//        if (idString.isEmpty() || passwordString.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Type your username and password",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
//        else if(data==null){
//            Toast.makeText(getApplicationContext(), "You are not registered yet",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
//        else if(!data.isEmpty()){
//            //Object[] arr = s.toArray();
//            //String userId = arr[0].toString();
//            //String pass = arr[1].toString();
//            //String name = arr[2].toString();
//            //String[] array = data.split("\\|\\|");
//            String[] array = data.split(Pattern.quote("|"));
//            String userId = array[0];
//            String pass = array[1];
//            String name = array[2];
//            //String email = array[3];
//
//            if(idString.equals(userId)  && passwordString.equals(pass)) {
//                Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
//                i.putExtra("id",userId);
//                ((EditText) findViewById(R.id.idField)).setText("");
//                ((EditText) findViewById(R.id.passwordField)).setText("");
//                Boolean firstLogin = true;
//                i.putExtra("FirstLogin",firstLogin);
//                startActivity(i);
//                ///Toast.makeText(getApplicationContext(), "Welcome "+name,
//                        //Toast.LENGTH_LONG).show();
//            }
//            else{
//                Toast.makeText(getApplicationContext(), "Invalid account, Try again ",
//                        Toast.LENGTH_LONG).show();
//                ((EditText) findViewById(R.id.idField)).setText("");
//                ((EditText) findViewById(R.id.passwordField)).setText("");
//            }
//
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "Invalid account, Try again",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
        //PREVIOUS ATTEMPT BEFORE USING PROGRAMMANAGER
//OLD UPTO HERE

        //NEW
//        if (idString.isEmpty() || passwordString.isEmpty()){
//            Toast.makeText(getApplicationContext(), "Type your username and password",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
//        else if(userObj.getId() == null){
//            Toast.makeText(getApplicationContext(), "You are not registered yet",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
//        else if(userObj.getId()!=null){
//            //String[] array = data.split(Pattern.quote("|"));
//
//            String userId = userObj.getId().toString();
//            String pass = userObj.getPassword().toString();
//            String name = userObj.getName().toString();
//            String email = userObj.getEmail().toString();
//            String numReport = userObj.getNumReport().toString();
//
//            if(idString.equals(userId)  && passwordString.equals(pass)) {
//                Intent i = new Intent(getApplicationContext(), HomepageActivity.class);
//                i.putExtra("id",userId);
//                ((EditText) findViewById(R.id.idField)).setText("");
//                ((EditText) findViewById(R.id.passwordField)).setText("");
//                Boolean firstLogin = true;
//                i.putExtra("FirstLogin",firstLogin);
//                startActivity(i);
//                ///Toast.makeText(getApplicationContext(), "Welcome "+name,
//                //Toast.LENGTH_LONG).show();
//            }
//            else{
//                Toast.makeText(getApplicationContext(), "Invalid account, Try again ",
//                        Toast.LENGTH_LONG).show();
//                ((EditText) findViewById(R.id.idField)).setText("");
//                ((EditText) findViewById(R.id.passwordField)).setText("");
//            }
//
//        }
//        else{
//            Toast.makeText(getApplicationContext(), "Invalid account, Try again",
//                    Toast.LENGTH_LONG).show();
//            ((EditText) findViewById(R.id.idField)).setText("");
//            ((EditText) findViewById(R.id.passwordField)).setText("");
//        }
        //

    }
    /**
     *navigate to MainActivity
     * @param view this allows to see cancel button
     */

    public void onClick_cancelButton(View view)
    {
//        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(i);
        finish();
    }
}
