package com.steapps.steapps.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NAAF on 30/06/2017.
 */

public class DBLocal {
    private static SharedPreferences sDbLocal;
    private static boolean isPrepared = false;

    public static void prepare(Activity activity) {
        String prefName = "DBLocal";
        int mode = Context.MODE_PRIVATE;
        sDbLocal = activity.getSharedPreferences(prefName, mode);
        DBLocal.isPrepared = true;
    }

    public static boolean isAlreadyLoggedIn() {
        if (sDbLocal.contains(DBKey.USER_IDCARD)) {
            return  true;
        } else {
            return  false;
        }
    }

    static void loggedInuser(JSONObject userData) {
        SharedPreferences.Editor editor = sDbLocal.edit();
        try {
            editor.putString(DBKey.USER_IDCARD, userData.getString(DBKey.USER_IDCARD));
            editor.putString(DBKey.USER_EMAIL, userData.getString(DBKey.USER_EMAIL));
            editor.putString(DBKey.USER_FULLNAME, userData.getString(DBKey.USER_FULLNAME));
            editor.putString(DBKey.USER_PASSWORD, userData.getString(DBKey.USER_PASSWORD));
            editor.putString(DBKey.USER_PHONE, userData.getString(DBKey.USER_PHONE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    static void loggedOutUser() {
        SharedPreferences.Editor editor = sDbLocal.edit();
        editor.remove(DBKey.USER_IDCARD);
        editor.remove(DBKey.USER_PASSWORD);
        editor.remove(DBKey.USER_FULLNAME);
        editor.remove(DBKey.USER_PHONE);
        editor.remove(DBKey.USER_EMAIL);
        editor.apply();
    }

//    public static void put(String key, String value) {
//        SharedPreferences.Editor editor =  sDbLocal.edit();
//        editor.putString(key, value);
//        editor.apply();
//    }
//
//    public static void put(String key, int value) {
//        SharedPreferences.Editor editor =  sDbLocal.edit();
//        editor.putInt(key, value);
//        editor.apply();
//    }
//
//    public static void put(String key, boolean value) {
//        SharedPreferences.Editor editor =  sDbLocal.edit();
//        editor.putBoolean(key, value);
//        editor.apply();
//    }

}
