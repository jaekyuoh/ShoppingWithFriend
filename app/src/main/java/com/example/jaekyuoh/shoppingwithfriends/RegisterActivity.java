package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 *this class represents Registration page and stores valid user information allotting credentials
 * @author Jaekyu Oh
 */

public class RegisterActivity extends ActionBarActivity {

    /** this variable is for shared preference file name*/
    private static final String PREFS = "credentialPrefs";
    //NEW
    //public static final String USERPREF = "newCredentials";
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    /**
     *navigate back to MainActivity
     * @param view this allows to see cancel button
     */
    public void onClick_toHomeButton(View view)
    {
//        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(i);
        finish();
    }
    /**
     *When Register button is clicked, this method checks whether information user given are all valid and if it is valid,
     * store the user's information, give credentials and redirect to Login page.
     * If information given is invalid, display error according to what type of error it is
     * Function is to register user given name, id, and password
     * @param view this allows to see register button
     */

    public void onClick_registerButton(View view)
    {
        SharedPreferences pref = getSharedPreferences(PREFS,0);
        //SharedPreferences.Editor editor = pref.edit();

        ///NEW ATTEMPT
//        SharedPreferences userPref = getSharedPreferences(USERPREF,MODE_PRIVATE);
//        SharedPreferences.Editor userPrefEditor = userPref.edit();

        ///

        EditText name = (EditText) findViewById(R.id.user_name);
        EditText id = (EditText) findViewById(R.id.user_id);
        EditText password = (EditText) findViewById(R.id.user_password);
        EditText passwordConfirm = (EditText) findViewById(R.id.user_password_confirmation);
        EditText emailEditText = (EditText) findViewById(R.id.email_editText);

        String nameString = name.getText().toString();
        String idString = id.getText().toString();
        String passwordString = password.getText().toString();
        String confirmString = passwordConfirm.getText().toString();
        String email = emailEditText.getText().toString();
        String report = "0";

        ///NEW
//        User userObj = new User(idString, nameString, passwordString, email, report);
//        Gson gson = new Gson();
//        String json = gson.toJson(userObj); //user data that has to be stored with idString as a key
        ///



        //LinkedHashSet<String> set = new LinkedHashSet<String>();
        //set.add(idString); //0
        //set.add(passwordString); //1
        //set.add(nameString); //2

        //ID, Password, Name, Email
        //String store = idString+"|"+passwordString+"|"+nameString + "|" + email + "|" + report;
//        Toast.makeText(getApplicationContext(), userObj.getEmail(),
//                Toast.LENGTH_LONG).show();
        //getString(String key, String defValue)

//PREVIOUS ATTEMPT
        if ((name!=null)&&(id!=null)&&(password!=null)){
            String result = ProgramManager.RegisterUser(idString,passwordString,nameString,email,report,pref,confirmString);
            if (result.equals("alreadyRegistered")){
                Toast.makeText(getApplicationContext(), "Username already exist.",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                finish();
                startActivity(i);

            }
            else if(result.equals("passwordMissMatch")){
                Toast.makeText(getApplicationContext(), "Password and confirmation password do not match",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                finish();
                startActivity(i);
            }
            else if(result.equals("success")){
                Toast.makeText(getApplicationContext(), "Successfully registered!",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                startActivity(i);
            }
//Previous attempt before using ProgramManager
//            if(pref.contains(idString)){
//                Toast.makeText(getApplicationContext(), "Username already exist.",
//                        Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                finish();
//                startActivity(i);
//            }
//            else if(!confirmString.equals(passwordString)){
//                Toast.makeText(getApplicationContext(), "Password and confirmation password do not match",
//                        Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                finish();
//                startActivity(i);
//            }
//            else if(confirmString.equals(passwordString)) {
//                if (!pref.contains(idString)) {
//                    //editor.putStringSet(idString, set);
//                    editor.putString(idString, store);
//                    editor.commit();
//                    Toast.makeText(getApplicationContext(), "Successfully registered!",
//                            Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                    finish();
//                    startActivity(i);
//                }
//            }
//Previous attempt before using ProgramManager STOPS HERE
        }
        ///////NEW ATTEMPT
//
//        if ((name!=null)&&(id!=null)&&(password!=null)){
//            if(userPref.contains(idString)){
//                Toast.makeText(getApplicationContext(), "Username already exist.",
//                        Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                finish();
//                startActivity(i);
//            }
//            else if(!confirmString.equals(passwordString)){
//                Toast.makeText(getApplicationContext(), "Password and confirmation password do not match",
//                        Toast.LENGTH_LONG).show();
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                finish();
//                startActivity(i);
//            }
//            else if(confirmString.equals(passwordString)) {
//                if (!userPref.contains(idString)) {
//                    //editor.putStringSet(idString, set);
//                    userPrefEditor.putString(idString, json);
//                    userPrefEditor.commit();
//                    Toast.makeText(getApplicationContext(), "Successfully registered!",
//                            Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                    finish();
//                    startActivity(i);
//                }
//            }
//        }
        ///////
        else{
            Toast.makeText(getApplicationContext(), "Fill out entire form",
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            finish();
            startActivity(i);
        }
    }

}
