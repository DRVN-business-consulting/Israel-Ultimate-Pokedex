package dev.f04c.pokemonapi;

import android.app.Application;

import dev.f04c.pokemonapi.model.retrofit.prefs.AppPreferences;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize AppPreferences or any other singletons
        AppPreferences.initialize(this);
    }
}