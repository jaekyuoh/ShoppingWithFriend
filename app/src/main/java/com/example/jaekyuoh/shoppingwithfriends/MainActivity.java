package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;

/**
 *This activity begins when you first start the app.
 * It contains Login Button and Register Button.
 * @author Jaekyu Oh
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     *this method starts LoginActivity when Login Button is clicked by user
     * @param view this allows to navigate to LoginActivity
     */

    public void onClick_toLoginButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
    /**
     *this method starts RegisterActivity when Register Button is clicked by user
     * @param view this allows to navigate to RegisterActivity
     */
    public void onClick_toRegisterButton(View view)
    {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }
}
