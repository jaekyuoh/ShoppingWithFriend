package com.example.jaekyuoh.shoppingwithfriends;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;
import android.test.InstrumentationTestCase;




// public static String AddFriend(String nameString, String emailString, String id, String store, SharedPreferences userList,SharedPreferences friendsList){

public class junitGL extends InstrumentationTestCase {
    private SharedPreferences useFriendsList;
    private SharedPreferences testFriendsList;
    private SharedPreferences testUserList;
    private Set<String> friends;
    private SharedPreferences.Editor testFriendsEditor;
    private SharedPreferences.Editor testUserEditor;



    protected void setUp() {
        Context context = null;

        context = getInstrumentation().getContext();
        useFriendsList = context.getSharedPreferences("useFriends", 0);
        testUserList = context.getSharedPreferences("testUser", 0);
        testFriendsList = context.getSharedPreferences("testFriends", 0);

        SharedPreferences.Editor testUserEditor = testUserList.edit();
    }

    public void testAdd() {
        SharedPreferences.Editor testFriendsEditor = testFriendsList.edit();
        SharedPreferences.Editor abc = useFriendsList.edit();
        abc.clear();
        abc.commit();
        testFriendsEditor.clear();
        testFriendsEditor.commit();

        String nameStringUser = "Adam";
        String emailStringUser = "adam@email.com";
        String idUser = "idAdam";
        String dataUser = "dataUser";
        String storeUser = "dataUser|idAdam";

        String nameString = "Bob";
        String emailString = "bob@email.com";
        String id = "idBob";
        String data = "dataBob";
        String store = "dataBob|idAdam";

        /*Set<String> insertingSet = new LinkedHashSet<String>();
        insertingSet.add(data + "|" + emailString + "|0|0|" + idUser);
        insertingSet.add(store);
        testFriendsEditor.putStringSet(id, insertingSet);
        testFriendsEditor.commit();*/

        ProgramManager.RegisterUser(idUser, "password", nameStringUser, emailStringUser, "0", testUserList, "password");
        ProgramManager.RegisterUser(id, "password", nameString, emailString, "0", testUserList, "password");

        String success = ProgramManager.AddFriend(nameString, emailString, idUser, data, testUserList, useFriendsList);
        //Set<String> friends = useFriendsList.getStringSet(idUser, new LinkedHashSet<String>());
        //Set<String> friendsTest = testFriendsList.getStringSet(id, new LinkedHashSet<String>());
        assertEquals(success, "success");
        //assertTrue(friends.contains(store));
        //assertEquals(friends, friendsTest);

    }

    public void testFail() {

        SharedPreferences.Editor abc = useFriendsList.edit();
        abc.clear();
        abc.commit();

        String status = "";

        String nameStringUser = "Adam";
        String emailStringUser = "adam@email.com";
        String idUser = "idAdam";
        String dataUser = "dataUser";
        String storeUser = "dataUser|idAdam";

        String nameString = "Bob";
        String emailString = "bob@email.com";
        String id = "idBob";
        String data = "dataBob";
        String store = "dataBob|idAdam";

        ProgramManager.RegisterUser(idUser, "password", nameStringUser, emailStringUser, "0", testUserList, "password");
        ProgramManager.RegisterUser(id, "password", nameString, emailString, "0", testUserList, "password");

        status = ProgramManager.AddFriend("", "", idUser, data, testUserList, useFriendsList);
        assertEquals("fillOut1", status);
        status = ProgramManager.AddFriend("fakeName", "fakeEmail", idUser, data, testUserList, useFriendsList);
        assertEquals("friendIsNotUser", status);
        status = ProgramManager.AddFriend("nameString", "", idUser, data, testUserList, useFriendsList);
        assertEquals("fillOut1", status);
        status = ProgramManager.AddFriend("", "emailString", idUser, data, testUserList, useFriendsList);
        assertEquals("fillOut1", status);
        status = ProgramManager.AddFriend(nameString, emailString, idUser, data, testUserList, useFriendsList);
        assertEquals("success", status);
        status = ProgramManager.AddFriend(nameString, emailString, idUser, data, testUserList, useFriendsList);
        assertEquals("alreadyInFriendList", status);
    }

 }

// public static String AddFriend(String nameString, String emailString, String id, String store, SharedPreferences userList,SharedPreferences friendsList){