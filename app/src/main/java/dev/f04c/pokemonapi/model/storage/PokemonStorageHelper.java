package dev.f04c.pokemonapi.model.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.f04c.pokemonapi.model.Pokemon;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PokemonStorageHelper {
    private static final String PREFS_NAME = "pokemon_prefs";
    private static final String POKEMON_KEY = "pokemon_key";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public PokemonStorageHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void savePokemons(List<Pokemon> pokemons) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pokemons);
        editor.putString("pokemons", json);
        editor.apply();
        Log.d("PokemonStorageHelper", "Pokemons saved: " + json);
    }


    public List<Pokemon> getSavedPokemons() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pokemons", null);
        Type type = new TypeToken<List<Pokemon>>() {}.getType();
        List<Pokemon> pokemons = gson.fromJson(json, type);
        Log.d("PokemonStorageHelper", "Retrieved pokemons: " + (pokemons != null ? pokemons.size() : "null"));
        return pokemons;
    }
}