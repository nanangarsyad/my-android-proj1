package com.steapps.steapps.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by NAAF on 30/06/2017.
 */

public class DBLocal {
    private static SharedPreferences sDbUser;
    private static SharedPreferences sDbForm;
    private static boolean isPrepared = false;

    public static void prepare(Activity activity) {
        int mode = Context.MODE_PRIVATE;
        String userPerf = "DBLocal_User";
        String formPerf = "DBLocal_Form";
        sDbUser = activity.getSharedPreferences(userPerf, mode);
        sDbForm = activity.getSharedPreferences(formPerf, mode);
        DBLocal.isPrepared = true;
    }

    public static class User {

        public static boolean isAlreadyLoggedIn() {
            if (sDbUser.contains(DBKey.USER_IDCARD)) {
                return  true;
            } else {
                return  false;
            }
        }

        static void loggedInuser(JSONObject userData) {
            SharedPreferences.Editor editor = sDbUser.edit();
            try {
                editor.putString(DBKey.USER_IDCARD, userData.getString(DBKey.USER_IDCARD));
                editor.putString(DBKey.USER_EMAIL, userData.getString(DBKey.USER_EMAIL));
                editor.putString(DBKey.USER_FULLNAME, userData.getString(DBKey.USER_FULLNAME));
                editor.putString(DBKey.USER_USERNAME, userData.getString(DBKey.USER_USERNAME));
                editor.putString(DBKey.USER_TTL, userData.getString(DBKey.USER_TTL));
                editor.putString(DBKey.USER_TANGGAL_MASUK, userData.getString(DBKey.USER_TANGGAL_MASUK));
                editor.putString(DBKey.USER_PASSWORD, userData.getString(DBKey.USER_PASSWORD));
                editor.putString(DBKey.USER_PHONE, userData.getString(DBKey.USER_PHONE));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            editor.apply();
        }

        static void loggedOutUser() {
            SharedPreferences.Editor editor = sDbUser.edit();
            editor.remove(DBKey.USER_IDCARD);
            editor.remove(DBKey.USER_PASSWORD);
            editor.remove(DBKey.USER_FULLNAME);
            editor.remove(DBKey.USER_USERNAME);
            editor.remove(DBKey.USER_TTL);
            editor.remove(DBKey.USER_TANGGAL_MASUK);
            editor.remove(DBKey.USER_PHONE);
            editor.remove(DBKey.USER_EMAIL);
            editor.apply();
        }

        public static String getStringValue(String key) {
            return getStringValue(key, null);
        }

        public static String getStringValue(String key, String def) {
            return sDbUser.getString(key, def);

        }

        public static boolean getBooleanValue(String key) {
            return getBooleanValue(key, false);
        }

        public static boolean getBooleanValue(String key, boolean def) {
            return sDbUser.getBoolean(key, def);
        }
    }

    public static class Form {

        public static boolean isFormReadyToSend() {
            for (String key: DBKey.ALL_FORM_KEYS) {
                if (!sDbForm.contains(key)) {
                    return false;
                }
            }
            return true;
        }

        public static Map<String, ?> asMap() {
            return sDbForm.getAll();
        }

        public static void clearAll() {
            sDbForm.edit().clear().apply();
        }

        public static void putValues(Object[] ... keyAndValues) {
            SharedPreferences.Editor editor = sDbForm.edit();
            for (Object[] pair : keyAndValues) {
                String key = (String) pair[0];
                Object value = pair[1];
                if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof  Float) {
                    editor.putFloat(key, (Float) value);
                } else if (value instanceof String) {
                    editor.putString(key, (String) value);
                } else if (value instanceof  Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                }
            }
            editor.apply();
        }

        public static void putValue(String key, String value) {
            SharedPreferences.Editor editor =  sDbForm.edit();
            editor.putString(key, value);
            editor.apply();
        }

        public static void putValue(String key, int value) {
            SharedPreferences.Editor editor =  sDbForm.edit();
            editor.putInt(key, value);
            editor.apply();
        }

        public static void putValue(String key, boolean value) {
            SharedPreferences.Editor editor =  sDbForm.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        public static int getIntValue(String key) {
            return getIntValue(key, 0);
        }

        public static int getIntValue(String key , int def) {
            return sDbForm.getInt(key, def);
        }

        public static String getStringValue(String key) {
            return getStringValue(key, null);
        }

        public static String getStringValue(String key, String def) {
            return sDbForm.getString(key, def);

        }

        public static boolean getBooleanValue(String key) {
            return getBooleanValue(key, false);
        }

        public static boolean getBooleanValue(String key, boolean def) {
            return sDbForm.getBoolean(key, def);
        }


    }


}

