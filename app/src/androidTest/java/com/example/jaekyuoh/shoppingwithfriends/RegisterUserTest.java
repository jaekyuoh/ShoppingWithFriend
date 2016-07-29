
package com.example.jaekyuoh.shoppingwithfriends;

        import android.content.Context;
        import android.content.SharedPreferences;
        import java.util.Set;
        import android.test.InstrumentationTestCase;




// public static String AddFriend(String nameString, String emailString, String id, String store, SharedPreferences userList,SharedPreferences friendsList){

public class RegisterUserTest extends InstrumentationTestCase {
    private SharedPreferences testUserList;
    private Set<String> friends;
    private SharedPreferences.Editor testUserEditor;



    protected void setUp() {
        Context context = null;

        context = getInstrumentation().getContext();
        testUserList = context.getSharedPreferences("testUser", 0);
        SharedPreferences.Editor testUserEditor = testUserList.edit();

    }

    public void testAdd() {

        String nameStringUser = "Adam";
        String emailStringUser = "adam@email.com";
        String idUser = "idAdam";
        String result;
        testUserEditor.clear();
        testUserEditor.commit();

        result = ProgramManager.RegisterUser(idUser, "password", nameStringUser, emailStringUser, "0", testUserList, "password");
        assertEquals(result, "success");


    }

    public void testFail() {

        String status = "";

        String nameStringUser = "Adam";
        String emailStringUser = "adam@email.com";
        String idUser = "idAdam";


        String nameString = "bob";
        String emailString = "bob@email.com";
        String id = "idBob";

        testUserEditor.clear();
        testUserEditor.commit();

        String result;


        result = ProgramManager.RegisterUser(idUser, "password", nameStringUser, emailStringUser, "0", testUserList, "password");
        assertEquals(result, "success");
        result = ProgramManager.RegisterUser(idUser, "password", nameStringUser, emailStringUser, "0", testUserList, "password");
        assertEquals(result, "alreadyRegistered");
        result = ProgramManager.RegisterUser(id, "password", nameString, emailString, "0", testUserList, "newPassword");
        assertEquals(result, "passwordMissMatch");
    }

}