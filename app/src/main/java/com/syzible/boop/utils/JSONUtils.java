package com.syzible.boop.utils;

import android.content.Context;

import com.syzible.boop.persistence.LocalPrefs;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ed on 22/11/2017.
 */

public class JSONUtils {
    public static JSONObject getIdPayload(Context context) {
        JSONObject o = new JSONObject();
        try {
            o.put("number", LocalPrefs.getPhoneNumber(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;
    }
}
