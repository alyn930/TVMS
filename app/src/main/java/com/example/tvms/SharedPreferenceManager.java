package com.example.tvms;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.StringRequest;

public class SharedPreferenceManager {
    private static SharedPreferenceManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "user_reference_info";
    private static final String DRIVER_ID = "driver_id";
    private static final String LICENSE_ID = "license_id";
    private static final String DRIVER_PINCODE = "driver_pincode";
    private static final String DRIVER_LNAME = "driver_lname";
    private static final String DRIVER_FNAME = "driver_fname";
    private static final String DRIVER_MI = "driver_mi";
    private static final String DRIVER_GENDER = "driver_gender";
    private static final String DRIVER_BIRTHDATE = "driver_birthdate";
    private static final String DRIVER_ADDRESSPROV = "driver_addressProv";
    private static final String DRIVER_ADDRESSCITY = "driver_addressCity";
    private static final String DRIVER_MOBILE = "driver_mobile";
    private static final String DRIVER_TEL = "driver_tel";
    private static final String DRIVER_TYPE = "driver_type";
    private static final String DRIVER_EMAIL = "driver_email";
    private static final String DRIVER_PASSWORD = "driver_password";

    private SharedPreferenceManager(Context context) { mCtx = context;}

    public static synchronized SharedPreferenceManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }

    public boolean driver_Login(String id, String license, String pincode, String lname, String fname, String mi,
                             String gender, String birthDate, String addressProv, String addressCity,
                             String mobile, String tel, String type, String email, String password){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(DRIVER_ID, id);
        editor.putString(LICENSE_ID, license);
        editor.putString(DRIVER_PINCODE, pincode);
        editor.putString(DRIVER_LNAME, lname);
        editor.putString(DRIVER_FNAME, fname);
        editor.putString(DRIVER_MI, mi);
        editor.putString(DRIVER_GENDER, gender);
        editor.putString(DRIVER_BIRTHDATE, birthDate);
        editor.putString(DRIVER_ADDRESSPROV, addressProv);
        editor.putString(DRIVER_ADDRESSCITY, addressCity);
        editor.putString(DRIVER_MOBILE, mobile);
        editor.putString(DRIVER_TEL, tel);
        editor.putString(DRIVER_TYPE, type);
        editor.putString(DRIVER_EMAIL, email);
        editor.putString(DRIVER_PASSWORD, password);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(DRIVER_EMAIL, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(DRIVER_EMAIL, "ERROR: PREF_MANAGER");
    }

    public String getSecurityKey(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DRIVER_PASSWORD, null);
    }

}
