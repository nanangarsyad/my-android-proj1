package com.steapps.steapps.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.steapps.steapps.BaseActivity;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by NAAF on 30/06/2017.
 */

public class DBServer {
    private static String TAG = DBServer.class.getSimpleName();
    private static SharedPreferences sUserDB;
    private static DatabaseReference sFbDatabase;
    private static boolean isPrepared = false;


    public static void prepare(Activity activity) {
        String prefName = "DBServer_User";
        int mode = Context.MODE_PRIVATE;
        sUserDB = activity.getSharedPreferences(prefName, mode);

        sFbDatabase  = FirebaseDatabase.getInstance().getReference();

        DBServer.isPrepared = true;
    }

    private static void sendRegisterRequest(
            final Map<String, String> stringMap, final Runnable onSuccess, final Runnable onFailed) {
        /**
         // old technique
         SharedPreferences.Editor editor = sUserDB.edit();
         editor.putString(idCard, jobj.toString());
         editor.apply();
         */
        DatabaseReference userRef = sFbDatabase.child("users");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // whatever the result
                userRef.removeEventListener(this);

                if (dataSnapshot.hasChild(stringMap.get(DBKey.USER_IDCARD))) {
                    onFailed.run();
                    return;
                }
                userRef.child(stringMap.get(DBKey.USER_IDCARD)).setValue(stringMap);
                onSuccess.run();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private static void sendLoginRequest(
            final String idCard, final String password,
            final Runnable onSuccess, final Runnable onFailed) {

        // old technique
        /*if (sUserDB.contains(idCard)) {
            try {
                JSONObject jsonObject = new JSONObject(sUserDB.getString(idCard, null));
                if (jsonObject.getString(DBKey.USER_PASSWORD).equals(password)) {
                    DBLocal.User.loggedInuser(jsonObject);
                    return new Status(1, "Success. Login with idCard " + idCard);
                } else {
                    return new Status(0, "Failed. Wrong password");
                }
            } catch (JSONException e) {
                return new Status(0, "Failed. idCard Not Registered");
            }
        } else {
            return new Status(0, "Failed. idCard Not Registered");
        }*/

        DatabaseReference userRef = sFbDatabase.child("users");
        //Log.d(getClass().getSimpleName(), "Called??0");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userRef.removeEventListener(this);

                Log.d(TAG, idCard + " <> "+ password);
                Log.d(TAG, String.valueOf(dataSnapshot.getValue()));
                if (dataSnapshot.hasChild(idCard) &&
                        dataSnapshot.child(idCard).child(DBKey.USER_PASSWORD).getValue().equals(password)) {
                    DBLocal.User.loggedInuser(new JSONObject((Map) dataSnapshot.child(idCard).getValue()));
                    Log.d(getClass().getSimpleName(), String.valueOf(dataSnapshot.child(idCard).getValue()));
                    onSuccess.run();
                    return;
                }
                onFailed.run();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void register(
            String fullName, String idCard, String password,
            String userName, String phoneNum, String email,
            String ttl, String tglMasuk, Status.Callback callback) {

        Map<String, String> map = new HashMap<>();
        map.put(DBKey.USER_FULLNAME, fullName);
        map.put(DBKey.USER_USERNAME, userName);
        map.put(DBKey.USER_TTL, ttl);
        map.put(DBKey.USER_TANGGAL_MASUK, tglMasuk);
        map.put(DBKey.USER_IDCARD, idCard);
        map.put(DBKey.USER_PASSWORD, password);
        map.put(DBKey.USER_PHONE, phoneNum);
        map.put(DBKey.USER_EMAIL, email);

        ACProgressFlower dialog = new ACProgressFlower.Builder(BaseActivity.sCurrentContext)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Please wait...")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();

        sendRegisterRequest(
                map,
                () -> {
                    dialog.dismiss();
                    callback.onDone(new Status(1, "Success. Registered user with idCard " + idCard));
                    },
                () -> {
                    dialog.dismiss();
                    callback.onDone(new Status(0, "Failed. User Already Exist"));
                    }
        );
    }

    public static void login(String idCard, String password, Status.Callback callback) {
        /*ACProgressFlower dialog = new ACProgressFlower.Builder(BaseActivity.sCurrentContext)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Please wait...")
                .fadeColor(Color.DKGRAY).build();

        dialog.show();*/
        LoadToast lt = new LoadToast(BaseActivity.sCurrentContext);
        lt.setText("Logging in...");
        lt.show();

        sendLoginRequest(idCard, password,
            () -> {
                //dialog.dismiss();
                lt.success();
                callback.onDone(new Status(1, "Success. Login with idCard " + idCard));
            },
            () -> {
                //dialog.dismiss();
                lt.error();
                callback.onDone(new Status(0, "Failed. Either pass wrong or idCard not found"));
            });
    }

    public static Status logout() {
        if (DBLocal.User.isAlreadyLoggedIn()) {
            DBLocal.User.loggedOutUser();
            return new Status(1, "Success. Logged out");
        } else {
            return new Status(0, "Failed. No user logged in");
        }
    }

    public static void sendFormToServer(Status.Callback callback) {

        if (!DBLocal.Form.isFormReadyToSend()) {
            callback.onDone(new Status(0, "Failed. You must fill all the form."));;
            return;
        }

        LoadToast lt = new LoadToast(BaseActivity.sCurrentContext);
        lt.setText("Sending data...");
        lt.show();

        DatabaseReference formsRef = sFbDatabase.child("forms")
                .child(DBLocal.User.getStringValue(DBKey.USER_IDCARD))
                .child(String.valueOf(Calendar.getInstance().getTimeInMillis()));

        Log.d(TAG, String.valueOf(DBLocal.Form.asMap()));
        formsRef.setValue(DBLocal.Form.asMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError != null) {
                    lt.error();
                    callback.onDone(
                            new Status(0, "Failed. "+ databaseError.getMessage()));
                } else {
                    lt.success();
                    callback.onDone(
                            new Status(1, "Success. Form sent"));
                }
            }
        });

    }

}
