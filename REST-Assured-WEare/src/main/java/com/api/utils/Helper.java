package com.api.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Helper {

    // Checks if the given string is a valid JSON object
    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
