package dev.f04c.pokemonapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.f04c.pokemonapi.model.Pokemon;
import dev.f04c.pokemonapi.model.adapter.PokemonAdapter;
import dev.f04c.pokemonapi.model.retrofit.api.API;
import dev.f04c.pokemonapi.model.retrofit.api.PokemonAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class PokemonList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list); // Ensure this layout file exists

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchPokemons();
    }

    private void fetchPokemons() {
        PokemonAPI pokemonApi = API.pokemonApi();
        Call<List<Pokemon>> call = pokemonApi.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemons = response.body();
                    // Check if the list is not empty
                    if (pokemons != null && !pokemons.isEmpty()) {
                        // Pass the context and the list to the adapter
                        adapter = new PokemonAdapter(PokemonList.this, pokemons);
                        recyclerView.setAdapter(adapter);
                        Log.d("PokemonList", "Pokemons loaded successfully: " + pokemons.size() + " items.");
                    } else {
                        Toast.makeText(PokemonList.this, "No Pokémon found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    handleApiError(response);
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.d("PokemonList", "Error fetching pokemons: " + t.getMessage(), t);
//                Toast.makeText(PokemonList.this, "Error fetching pokemons", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleApiError(Response<List<Pokemon>> response) {
        try {
            String errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
            Log.d("PokemonList", "Failed to load pokemons. Response code: " + response.code() + ". Error body: " + errorBody);
//            Toast.makeText(PokemonList.this, "Failed to load pokemons", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("PokemonList", "Failed to read error body", e);
//            Toast.makeText(PokemonList.this, "Error parsing response", Toast.LENGTH_SHORT).show();
        }
    }
}