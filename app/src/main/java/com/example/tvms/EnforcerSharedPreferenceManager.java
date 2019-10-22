package com.example.tvms;

import android.content.Context;
import android.content.SharedPreferences;

public class EnforcerSharedPreferenceManager {
    private static EnforcerSharedPreferenceManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "user_reference_info";
    private static final String ENFORCER_ID = "enforcer_id";
    private static final String ENFORCER_LNAME = "enforcer_lname";
    private static final String ENFORCER_FNAME = "enforcer_fname";
    private static final String ENFORCER_MI = "enforcer_mi";
    private static final String ENFORCER_ADDRESSPROV = "enforcer_addressProve";
    private static final String ENFORCER_ADDRESSCITY = "enforcer_addressCity";
    private static final String ENFORCER_MOBILE = "enforcer_mobile";
    private static final String ENFORCER_TEL = "enforcer_tel";
    private static final String ENFORCER_GENDER = "enforcer_gender";
    private static final String ENFORCER_EMAIL = "enforcer_email";
    private static final String ENFORCER_PASSWORD = "enforcer_password";
    private static final String ENFORCER_TYPE = "enforcer_type";
    private static final String ENFORCER_BIRTHDATE = "enforcer_birthdate";
    private static final String AGENCY_ID = "agency_id";

    private EnforcerSharedPreferenceManager(Context context) { mCtx = context;}

    public static synchronized EnforcerSharedPreferenceManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new EnforcerSharedPreferenceManager(context);
        }
        return mInstance;
    }

    public boolean enforcer_Login(String id, String lname, String fname, String mi, String addressProv, String addressCity,
                                String mobile, String tel, String gender, String email, String password, String type,
                                String birthdate, String agency){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(ENFORCER_ID, id);
        editor.putString(ENFORCER_LNAME, lname);
        editor.putString(ENFORCER_FNAME, fname);
        editor.putString(ENFORCER_MI, mi);
        editor.putString(ENFORCER_ADDRESSPROV, addressProv);
        editor.putString(ENFORCER_ADDRESSCITY, addressCity);
        editor.putString(ENFORCER_MOBILE, mobile);
        editor.putString(ENFORCER_TEL, tel);
        editor.putString(ENFORCER_GENDER, gender);
        editor.putString(ENFORCER_EMAIL, email);
        editor.putString(ENFORCER_PASSWORD, password);
        editor.putString(ENFORCER_TYPE, type);
        editor.putString(ENFORCER_BIRTHDATE, birthdate);
        editor.putString(AGENCY_ID, agency);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(ENFORCER_EMAIL, null) != null){
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
        return  sharedPreferences.getString(ENFORCER_EMAIL, "ERROR: PREF_MANAGER");
    }

    public String getSecurityKey(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ENFORCER_PASSWORD, null);
    }
}
