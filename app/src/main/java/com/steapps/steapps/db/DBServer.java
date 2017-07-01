package com.steapps.steapps.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.steapps.steapps.Status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NAAF on 30/06/2017.
 */

public class DBServer {
    private static SharedPreferences sUserDB;
    private static boolean isPrepared = false;


    public static void prepare(Activity activity) {
        String prefName = "DBServer_User";
        int mode = Context.MODE_PRIVATE;
        sUserDB = activity.getSharedPreferences(prefName, mode);
        DBServer.isPrepared = true;
    }

    public static Status register(
            String fullName, String idCard, String password,
            String phoneNum, String email) {

        if (sUserDB.contains(idCard)) {
            return new Status(0, "Failed. User Already Exist");
        }

        JSONObject jobj = new JSONObject();
        try {
            jobj.put(DBKey.USER_FULLNAME, fullName);
            jobj.put(DBKey.USER_IDCARD, idCard);
            jobj.put(DBKey.USER_PASSWORD, password);
            jobj.put(DBKey.USER_PHONE, phoneNum);
            jobj.put(DBKey.USER_EMAIL, email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sUserDB.edit();
        editor.putString(idCard, jobj.toString());
        editor.apply();
        return new Status(1, "Success. Registered user with idCard " + idCard);
    }

    public static Status login(String idCard, String password) {
        if (sUserDB.contains(idCard)) {
            try {
                JSONObject jsonObject = new JSONObject(sUserDB.getString(idCard, null));
                if (jsonObject.getString(DBKey.USER_PASSWORD).equals(password)) {
                    DBLocal.loggedInuser(jsonObject);
                    return new Status(1, "Success. Login with idCard " + idCard);
                } else {
                    return new Status(0, "Failed. Wrong password");
                }
            } catch (JSONException e) {
                return new Status(0, "Failed. idCard Not Registered");
            }
        } else {
            return new Status(0, "Failed. idCard Not Registered");
        }
    }

    public static Status logout() {
        if (DBLocal.isAlreadyLoggedIn()) {
            DBLocal.loggedOutUser();
            return new Status(1, "Success. Logged out");
        } else {
            return new Status(0, "Failed. No user logged in");
        }
    }

}
