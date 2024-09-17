package dev.f04c.pokemonapi.model.retrofit.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {


    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static AppPreferences instance;

    private SharedPreferences sharedPreferences;

    private AppPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    // Initialize the singleton instance
    public static void initialize(Context context) {
        if (instance == null) {
            instance = new AppPreferences(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE));
        }
    }

    // Get the singleton instance
    public static AppPreferences getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AppPreferences not initialized");
        }
        return instance;
    }

    // Save the access token
    public void setAccessToken(String accessToken) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    // Retrieve the access token
    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }
}