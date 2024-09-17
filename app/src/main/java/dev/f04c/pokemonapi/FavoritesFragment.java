package dev.f04c.pokemonapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.f04c.pokemonapi.model.Pokemon;
import dev.f04c.pokemonapi.model.adapter.PokemonAdapter;
import dev.f04c.pokemonapi.model.storage.FavoritesManager;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textViewNoFavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Initialize RecyclerView and TextView
        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        textViewNoFavorites = view.findViewById(R.id.textViewNoFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load favorited Pokémon
        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        FavoritesManager favoritesManager = FavoritesManager.getInstance(getContext());
        List<Pokemon> favorites = favoritesManager.getFavoritedPokemons();

        if (favorites != null && !favorites.isEmpty()) {
            PokemonAdapter adapter = new PokemonAdapter(getContext(), favorites);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            textViewNoFavorites.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            textViewNoFavorites.setVisibility(View.VISIBLE);
        }
    }
}