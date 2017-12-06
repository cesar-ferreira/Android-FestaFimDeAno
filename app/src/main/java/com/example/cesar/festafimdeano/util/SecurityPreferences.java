package com.example.cesar.festafimdeano.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cesar on 06/12/17.
 */

public class SecurityPreferences {

    private final SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context){
        this.mSharedPreferences = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key,"");
    }
}
