package com.steapps.steapps.db;

/**
 * Created by NAAF on 30/06/2017.
 */

public class Status {
    public int code;
    public String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSucces() {
        return code == 1;
    }

     public interface Callback {

        void onDone(Status status);
    }

}
