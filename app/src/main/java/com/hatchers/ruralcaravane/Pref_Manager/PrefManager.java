package com.hatchers.ruralcaravane.Pref_Manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ashwin on 03-Dec-17.
 */

public class PrefManager {
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "rural_caravane";

    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String MOBILE = "mobile";


    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";


    public PrefManager(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedpreferences.edit();
    }


    public void createLogin(String mobile) {
        editor.putString(MOBILE, mobile);
        //editor.putString(PASSWORD, password);

        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }


    public boolean isLoggedIn() {
        return sharedpreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }


    public boolean setLogOut() {
        return sharedpreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }


    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getUserName() {
        return sharedpreferences.getString(USER_NAME, null);
    }


    public void setPassword(String password) {
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public String getPassword() {
        return sharedpreferences.getString(PASSWORD, null);
    }



    public void setMobile(String mobile) {
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public String getMobile() {
        return sharedpreferences.getString(MOBILE, null);
    }


}
