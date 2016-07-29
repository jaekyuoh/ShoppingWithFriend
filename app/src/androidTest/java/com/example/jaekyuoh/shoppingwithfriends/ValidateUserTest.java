package com.example.jaekyuoh.shoppingwithfriends;

import android.test.InstrumentationTestCase;

/**
 * Created by jaekyuoh on 4/3/15.
 */
public class ValidateUserTest extends InstrumentationTestCase {

    //When user successfully login
    public void testValidateUser1() {
        String data = "user1|123|name1|email1|0";
        String idString ="user1";
        String passwordString = "123";

        String result = ProgramManager.ValidateUser(idString,passwordString,data);
//        assertTrue(result.equals("login"));
        assertEquals(result,"login");


    }

    //When user did not fill in the blank space for id and password
    public void testValidateUser2() {
        String data = "user1|123|name1|email1|0";
        String idString ="user1";
        String passwordString = "123";

        String result = ProgramManager.ValidateUser("","",data);
        assertEquals(result,"fillOut");

    }

    //When idString and passwordString is not in credential data
    public void testValidateUser3() {
        //String data = "user1|123|name1|email1|0";

        String idString ="user1";
        String passwordString = "123";

        String result = ProgramManager.ValidateUser(idString, passwordString,null);
        assertEquals(result,"notRegistered");

    }
}
