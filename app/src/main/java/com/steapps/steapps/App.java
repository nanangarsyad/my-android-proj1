package com.steapps.steapps;

import android.app.Application;

import org.polaric.colorful.Colorful;

/**
 * Created by NAAF on 31/07/2017.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.INDIGO)
                .accentColor(Colorful.ThemeColor.LIGHT_BLUE)
                .translucent(false)
                .dark(false);
        Colorful.init(this);
    }
}
