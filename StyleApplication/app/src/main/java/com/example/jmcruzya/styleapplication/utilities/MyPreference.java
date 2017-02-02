package com.example.jmcruzya.styleapplication.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jmcruzya on 01/02/2017.
 */

public class MyPreference {

    private static final String PREF_NAME = "MyData";
    private static final int PRIVATE_MODE = 0;
    private static final String IS_LOGIN = "is_login";
    private static final String USER_NAME = "user_name";
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MyPreference(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLogin()
    {
        return pref.getBoolean(IS_LOGIN,false);
    }

    public void setStateLogin(boolean state)
    {
        editor.putBoolean(IS_LOGIN,state);
        editor.apply();
    }


    public String getUserName()
    {
        return pref.getString(USER_NAME,"");
    }

    public void setUserName(String userName)
    {
        editor.putString(USER_NAME,userName);
        editor.apply();
    }
}
