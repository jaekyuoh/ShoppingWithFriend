package com.example.jaekyuoh.shoppingwithfriends;

import android.content.SharedPreferences;
import android.test.InstrumentationTestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by EllisCooper on 4/3/15.
 */
public class AddItemTest extends InstrumentationTestCase {
    private static SharedPreferences testPref;
    //Tests appropriate coordinates are returned for a valid address
    public void testAddItem1() {
        testPref = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Override
            public String getString(String key, String defValue) {
                return null;
            }

            @Override
            public Set<String> getStringSet(String key, Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
        String item = "name";
        String price = "price";
        String location = "location";
        String userId = "id";
        String store = "store";

        String result = ProgramManager.AddItem(item, price, location, userId, store, testPref);

        assertEquals(result, "success");
    }

    //Tests default coordinates of (0, 0) are returned for invalid address
    public void testAddItem2() {
        testPref = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Override
            public String getString(String key, String defValue) {
                return null;
            }

            @Override
            public Set<String> getStringSet(String key, Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
        String item = "";
        String price = "price";
        String location = "location";
        String userId = "id";
        String store = "";

        String result = ProgramManager.AddItem(item, price, location, userId, store, testPref);

        assertEquals(result, "fillOut");
    }

    public void testAddItem3() {
        testPref = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Override
            public String getString(String key, String defValue) {
                return null;
            }

            @Override
            public Set<String> getStringSet(String key, Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
        String item = "item";
        String price = "price";
        String location = "";
        String userId = "id";
        String store = "";

        String result = ProgramManager.AddItem(item, price, location, userId, store, testPref);

        assertEquals(result, "fillOut");
    }

    public void testAddItem4() {
        testPref = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Override
            public String getString(String key, String defValue) {
                return null;
            }

            @Override
            public Set<String> getStringSet(String key, Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
        String item = "";
        String price = "price";
        String location = "location";
        String userId = "id";
        String store = "store";

        String result = ProgramManager.AddItem(item, price, location, userId, store, testPref);

        assertEquals(result, "fillOut");
    }
}
