package com.aenatural.aenaturals.baseframework;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private Context context;
    public static final String AENATURALS = "AE Naturals";
    public static final String U_TOKEN = "token";
    public static final String IS_LOGIN = "Login_status";
    public static final String Login_Section = "Login_Section";
    public static final String U_ID = "user_id";
    public static final String U_Name = "user_name";
    public static final String U_Email = "user_email";
    public static final String U_Mobile = "user_mobile";
    public static final String U_Profile_Pic = "profile_picture";
    public static final String HAS_SESSION = "has_session";
    public static final String IS_UPDATED = "is_updated";
    public static final String CATOGERY_ID = "categoryId";


    public Session(Context baseApbcContext) {
        this.context = baseApbcContext;
        prefs = context.getSharedPreferences(AENATURALS, Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }

    public boolean getHasSession() {
        return prefs.getBoolean(HAS_SESSION, false);
    }

    public void setHasSession(Boolean value) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putBoolean(HAS_SESSION, value);
        edits.apply();
    }

    public String getToken() {
        return prefs.getString(U_TOKEN, null);
    }

    public void setToken(String value) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(U_TOKEN, value);
        edits.apply();
    }

    public void setcategoryId(String value) {
        editor.putString(CATOGERY_ID,value);
        editor.apply();
    }

    public String getcategoryId() {
        return prefs.getString(CATOGERY_ID,null);
    }
    public Boolean getIsUpdateRequired() {
        return prefs.getBoolean(IS_UPDATED, false);
    }

    public void setIsUpdateRequired(Boolean value) {
        SharedPreferences.Editor edits = prefs.edit();
        edits.putBoolean(IS_UPDATED, value);
        edits.apply();
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }

    public Boolean getIsLogin() {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(String email, int section) {

        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(U_Email, email);
        edits.putInt(Login_Section, section);
        edits.putBoolean(IS_LOGIN, true);
        edits.apply();
    }
    public int loginSection() {
        return prefs.getInt(Login_Section, 1);
    }



}

