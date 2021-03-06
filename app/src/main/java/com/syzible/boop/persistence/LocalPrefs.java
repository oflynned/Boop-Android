package com.syzible.boop.persistence;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by ed on 22/11/2017.
 */

public class LocalPrefs {
    public enum Pref {
        phone_number, forename, surname, first_run_completed
    }

    public static String getPhoneNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Pref.phone_number.name(), "");
    }

    public static String getStringPref(Pref key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key.name(), "");
    }

    public static String getFullName(Context context) {
        return getStringPref(Pref.forename, context) + " " + getStringPref(Pref.surname, context);
    }

    public static boolean getBooleanPref(Pref key, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key.name(), false);
    }

    public static void setStringPref(Pref key, String value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(key.name(), value).apply();
    }

    public static void setIntPref(Pref key, int value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(key.name(), value).apply();
    }

    public static void setBooleanPref(Pref key, boolean value, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putBoolean(key.name(), value).apply();
    }

    public static void purgePref(Pref key, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(key.name(), "").apply();
    }

    public static boolean isLoggedIn(Context context) {
        return !getPhoneNumber(context).equals("");
    }

    public static boolean isFirstRunCompleted(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Pref.first_run_completed.name(), false);
    }
}
