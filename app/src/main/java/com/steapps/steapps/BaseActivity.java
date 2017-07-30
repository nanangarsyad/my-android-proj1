package com.steapps.steapps;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.polaric.colorful.CActivity;
import org.polaric.colorful.Colorful;

/**
 * Created by NAAF on 22/07/2017.
 */

public abstract  class BaseActivity extends CActivity {
    public static Context sCurrentContext;

    public BaseActivity() {
        sCurrentContext = this;
        Log.d("Base_" + getClass().getSimpleName(), String.valueOf(sCurrentContext));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.INDIGO)
                .accentColor(Colorful.ThemeColor.LIGHT_BLUE)
                .translucent(false)
                .dark(true);
        Colorful.init(this);
    }


}
