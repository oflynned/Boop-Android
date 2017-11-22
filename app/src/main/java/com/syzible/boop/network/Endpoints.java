package com.syzible.boop.network;

import android.os.Build;

/**
 * Created by ed on 17/05/2017.
 */

public class Endpoints {
    private static final int API_VERSION = 1;

    private static final String LOCAL_ENDPOINT = "http://10.0.2.2:3000";
    private static final String APP_ENDPOINT = "https://boop.herokuapp.com";

    private static final String STEM_URL = isDebugMode() ? LOCAL_ENDPOINT : APP_ENDPOINT;
    private static final String API_URL = STEM_URL + "/api/v" + API_VERSION;

    private static boolean isDebugMode() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    // services
    public static final String VERIFY_PHONE_NUMBER = API_URL + "/services/verify-number";

    // users
    public static final String CREATE_USER = API_URL + "/user/create";
    public static final String GET_USER_CONTACTS = API_URL + "/user/get-others";
    public static final String EDIT_USER_FCM = API_URL + "/user/edit-fcm";
}
