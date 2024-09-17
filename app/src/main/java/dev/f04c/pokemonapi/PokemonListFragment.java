package dev.f04c.pokemonapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import dev.f04c.pokemonapi.model.Pokemon;
import dev.f04c.pokemonapi.model.adapter.PokemonAdapter;
import dev.f04c.pokemonapi.model.retrofit.api.API;
import dev.f04c.pokemonapi.model.retrofit.api.PokemonAPI;
import dev.f04c.pokemonapi.model.storage.PokemonStorageHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class PokemonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private PokemonStorageHelper pokemonStorageHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize PokemonStorageHelper
        pokemonStorageHelper = new PokemonStorageHelper(requireContext());

        // Load saved Pokémon data first
        List<Pokemon> savedPokemons = pokemonStorageHelper.getSavedPokemons();
        if (savedPokemons != null && !savedPokemons.isEmpty()) {
            adapter = new PokemonAdapter(requireContext(), savedPokemons);
            recyclerView.setAdapter(adapter);
        }

        // Fetch Pokémon data from API
        fetchPokemons();

        return view;
    }

    private void fetchPokemons() {
        PokemonAPI pokemonApi = API.pokemonApi();
        Call<List<Pokemon>> call = pokemonApi.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemons = response.body();
                    if (pokemons != null && !pokemons.isEmpty()) {
                        // Save Pokémon data to SharedPreferences
                        pokemonStorageHelper.savePokemons(pokemons);

                        // Update RecyclerView
                        adapter = new PokemonAdapter(requireContext(), pokemons);
                        recyclerView.setAdapter(adapter);

                        Log.d("PokemonListFragment", "Pokemons loaded successfully: " + pokemons.size() + " items.");
                    } else {
//                        Toast.makeText(getContext(), "No Pokémon found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    handleApiError(response);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.d("PokemonListFragment", "Error fetching pokemons: " + t.getMessage(), t);
//                Toast.makeText(getContext(), "Error fetching pokemons", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleApiError(Response<List<Pokemon>> response) {
        try {
            String errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
            Log.d("PokemonListFragment", "Failed to load pokemons. Response code: " + response.code() + ". Error body: " + errorBody);
            Toast.makeText(getContext(), "Failed to load pokemons", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("PokemonListFragment", "Failed to read error body", e);
            Toast.makeText(getContext(), "Error parsing response", Toast.LENGTH_SHORT).show();
        }
    }
}