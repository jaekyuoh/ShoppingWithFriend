package com.example.jaekyuoh.shoppingwithfriends;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by jaekyuoh on 3/26/15.
 */
public class ProgramManager {
    //public static final String PREFS = "credentialPrefs";

    /**
     * Register user if credentials are valid.
     *
     * @param idString preferred id
     * @param passwordString preferred password
     * @param nameString name of user
     * @param email email of user
     * @param report #of report user made, 0 is assigned for new user
     * @param pref SharedPreference object
     * @param confirmString confirmed password that has to match with passwordString
     * @return String of message
     */
    public static String RegisterUser(String idString, String passwordString, String nameString, String email, String report, SharedPreferences pref, String confirmString){
        //SharedPreferences pref = getSharedPreferences(PREFS,0);
        SharedPreferences.Editor editor = pref.edit();
        String store = idString+"|"+passwordString+"|"+nameString + "|" + email + "|" + report;
        String alreadyRegistered;
        String passwordMissMatch;
        String success;
        if(pref.contains(idString)){
            alreadyRegistered = "alreadyRegistered";
            return alreadyRegistered;
        }
        else if(!confirmString.equals(passwordString)){
            passwordMissMatch = "passwordMissMatch";
            return passwordMissMatch;
        }
        else if(confirmString.equals(passwordString)) {
            if (!pref.contains(idString)) {
                //editor.putStringSet(idString, set);
                editor.putString(idString, store);
                editor.commit();
                success ="success";
                return success;
            }
        }
        return null;

    }

    /**
     * Validates user before he login. Rejects login when it is invalid.
     *
     * @param idString user id
     * @param passwordString user password
     * @param data information of user from SharedPreference
     * @return String of message
     */
    public static String ValidateUser(String idString, String passwordString, String data){
        String fillOut;
        String notRegistered;
        String invalidAccount;
        String login;
        if (idString.isEmpty() || passwordString.isEmpty()){
            fillOut = "fillOut";
            return fillOut;
        }
        else if(data==null){
            notRegistered = "notRegistered";
            return notRegistered;
        }
        else if(!data.isEmpty()){
            String[] array = data.split(Pattern.quote("|"));
            String userId = array[0];
            String pass = array[1];
            //String name = array[2];
            //String email = array[3];

            if(idString.equals(userId)  && passwordString.equals(pass)) {
                login = "login";
                return login;
            }
            else{
                invalidAccount = "invalidAccount";
                return invalidAccount;
            }

        }

        return null;
    }

    /**
     * Adds Friend
     *
     * @param nameString name of friend
     * @param emailString email of friend
     * @param id id of user
     * @param store data to be stored
     * @param userList SharedPreference of users
     * @param friendsList SharedPreference of friends
     * @return String of message
     */

    public static String AddFriend(String nameString, String emailString, String id, String store, SharedPreferences userList,SharedPreferences friendsList){
        Set<String> friends = friendsList.getStringSet(id, new LinkedHashSet<String>());
        Map<String,?> keys = userList.getAll();
        SharedPreferences.Editor friendsEditor = friendsList.edit();

        boolean exist=false;
        String friendId;

        String fillOut1;
        String alreadyInFriendList;
        String success;
        String fillOut2;
        String friendIsNotUser;

        for(Map.Entry<String,?> entry : keys.entrySet()){
            String idKey = entry.getKey();
            String data = entry.getValue().toString();
            String[] array = data.split(Pattern.quote("|"));
            //String userId = array[0];
            //String pass = array[1];
            String storedName = array[2];
            //String email2 = array[3];
            if (storedName.equals(nameString)){
                exist = true;
                friendId = idKey;
                store = store + "|" + friendId;

            }
        }
        if ((nameString.isEmpty()) && (emailString.isEmpty())) {
            fillOut1 = "fillOut1";
            return fillOut1;
        }
        else if(exist) {
            if (!(nameString.isEmpty()) && !(emailString.isEmpty())) {
                if (friends.contains(store)) {
                    alreadyInFriendList ="alreadyInFriendList";
                    return alreadyInFriendList;
                } else {
                    Set<String> insertingSet = new LinkedHashSet<String>();
                    Set<String> set = friendsList.getStringSet(id, new LinkedHashSet<String>());
                    Object[] arr = set.toArray();
                    for(int i=0; i<arr.length;i++){
                        String str = arr[i].toString();
                        String[] array = str.split(Pattern.quote("|"));
                        String name1 = array[0];
                        String email1 = array[1];
                        String friendId1 = array[4];
                        insertingSet.add(name1+"|"+email1+"|"+"0"+"|"+"0"  + "|"+ friendId1);
                    }
                    insertingSet.add(store);
                    friendsEditor.putStringSet(id, insertingSet);
                    friendsEditor.commit();
                    success = "success";
                    return success;
                }
            } else {

                fillOut2 = "fillOut2";
                return fillOut2;

            }
        }
        else if(!exist){

            friendIsNotUser="friendIsNotUser";
            return friendIsNotUser;
        }
        return null;
    }

    /**
     * Adds Item
     * @param itemString name of item
     * @param priceThresholdString price threshold of item
     * @param locationString location of item
     * @param id user id
     * @param store data to be stored
     * @param itemInfo SharedPreference of items
     * @return String of message
     */
    public static String AddItem(String itemString, String priceThresholdString, String locationString,String id, String store,SharedPreferences itemInfo){
        //SharedPreferences.Editor editor = itemInfo.edit();

        String success;
        String fillOut;

        if (!(itemString.isEmpty()) && !(priceThresholdString.isEmpty())&& !(locationString.isEmpty())) {
            Set<String> insertingSet = new LinkedHashSet<String>();
            //Set<String> set = itemInfo.getStringSet(id, new LinkedHashSet<String>());
            Set<String> set = new HashSet<String>(10);
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
            //editor.putStringSet(id, insertingSet);
            //editor.commit();


            success="success";
            return success;
        }
        else{
            fillOut = "fillOut";
            return fillOut;
        }

    }

    /**
     * updates friend for other users due to current user's report
     * @param friendsList SharedPreference of friends
     * @param id id of user
     * @return list of users that have been updated due to current user's report
     */
    public static ArrayList<String> updateFriend(SharedPreferences friendsList, String id){
        SharedPreferences.Editor friendsEditor = friendsList.edit();
        Map<String,?> keys = friendsList.getAll();
        ArrayList<String> result = new ArrayList<String>();

        for(Map.Entry<String,?> entry : keys.entrySet()) {
            String idKey = entry.getKey();
            //Set<String> tempSet = new LinkedHashSet<String>();
            //String[] strl = entry.getValue();
            Set<Object> temp = (Set<Object>) entry.getValue();
            Object[] tempArray = (Object[])temp.toArray();
            for (int ind = 0; ind < tempArray.length; ind++){
                String d = tempArray[ind].toString();
                String[] array2 = d.split(Pattern.quote("|"));
                //String name2 = array2[0];
                //String email2 = array2[1];
                //String rate2 = array2[2];
                //String report2 = array2[3];
                String friendId2 = array2[4];
                //Toast.makeText(getApplicationContext(), "SharedPreferences: "+ entry.getKey() + ":" + entry.getValue(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), d, Toast.LENGTH_LONG).show();

                //Log.v("SharedPreferences", entry.getKey() + ":" + entry.getValue().toString());
                //Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();

                if(friendId2.equals(id)){
                    //when this user is in someone's friend list
                    //Toast.makeText(getApplicationContext(), "firendId2 = " + friendId2 +"id = " + id + "idKey = "+idKey, Toast.LENGTH_LONG).show();

                    //int reportNum = Integer.parseInt(report2);
                    //int newNum = reportNum + 1;
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
                            //Toast.makeText(getApplicationContext(), "Updated " + idKey + "'s friendList!!", Toast.LENGTH_LONG).show();
                            result.add(idKey);
                        }
                        else{
                            insertingSet2.add(name1 + "|" + email1 + "|" + rate1 + "|" + reportStr + "|" + friendId1);
                        }

                    }
                    friendsEditor.putStringSet(idKey, insertingSet2);
                    friendsEditor.commit();

                }
            }
        }
        return result;
    }




}
