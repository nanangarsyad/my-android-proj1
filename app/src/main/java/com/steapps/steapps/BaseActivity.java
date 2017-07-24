package com.steapps.steapps;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by NAAF on 22/07/2017.
 */

public abstract  class BaseActivity extends AppCompatActivity {
    public static Context sCurrentContext;

    public BaseActivity() {
        sCurrentContext = this;
        Log.d("Base_" + getClass().getSimpleName(), String.valueOf(sCurrentContext));
    }
}
