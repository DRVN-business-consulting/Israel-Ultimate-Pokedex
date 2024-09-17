package dev.f04c.pokemonapi.model.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.f04c.pokemonapi.model.Pokemon;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static final String PREFS_NAME = "pokemon_prefs";
    private static final String FAVORITES_KEY = "favorites_key";
    private static FavoritesManager instance;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private FavoritesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static FavoritesManager getInstance(Context context) {
        if (instance == null) {
            instance = new FavoritesManager(context);
        }
        return instance;
    }

    public void addFavoritePokemon(Pokemon pokemon) {
        List<Pokemon> favorites = getFavoritedPokemons();
        if (favorites == null) {
            favorites = new ArrayList<>();
        }
        if (!isFavorite(pokemon)) {
            favorites.add(pokemon);
            saveFavorites(favorites);
        }
    }

    public void removeFavoritePokemon(Pokemon pokemon) {
        List<Pokemon> favorites = getFavoritedPokemons();
        if (favorites != null) {
            for (int i = 0; i < favorites.size(); i++) {
                if (favorites.get(i).getId() == pokemon.getId()) {
                    favorites.remove(i);
                    saveFavorites(favorites);
                    break;
                }
            }
        }
    }

    public List<Pokemon> getFavoritedPokemons() {
        String json = sharedPreferences.getString(FAVORITES_KEY, null);
        Type type = new TypeToken<List<Pokemon>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveFavorites(List<Pokemon> favorites) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(favorites);
        editor.putString(FAVORITES_KEY, json);
        editor.apply();
    }

    public boolean isFavorite(Pokemon pokemon) {
        List<Pokemon> favorites = getFavoritedPokemons();
        if (favorites != null) {
            for (Pokemon fav : favorites) {
                // Assumes that Pokémon are compared by their unique ID
                if (fav.getId() == pokemon.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
}