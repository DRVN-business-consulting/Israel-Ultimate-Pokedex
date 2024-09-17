package dev.f04c.pokemonapi.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import dev.f04c.pokemonapi.R;
import dev.f04c.pokemonapi.model.Pokemon;
import dev.f04c.pokemonapi.model.storage.FavoritesManager;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private final List<Pokemon> pokemons;
    private final FavoritesManager favoritesManager;

    public PokemonAdapter(Context context, List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        this.favoritesManager = FavoritesManager.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        String pokemonName = pokemon.getName().getEnglish();

        // Set the Pokemon name
        holder.pokemonName.setText(pokemonName);

        // Construct the image URL
        String imageUrl = "http://192.169.14.109:9090/image/hi_res/" + pokemon.getId() + ".png";

        // Load the Pokemon image using Glide
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.pokemonImage);

        // Update heart icon based on favorite status
        holder.favoriteIcon.setText(favoritesManager.isFavorite(pokemon) ? "❤️" : "🤍");

        // Handle heart icon click
        holder.favoriteIcon.setOnClickListener(v -> {
            if (favoritesManager.isFavorite(pokemon)) {
                favoritesManager.removeFavoritePokemon(pokemon);
                holder.favoriteIcon.setText("🤍");
            } else {
                favoritesManager.addFavoritePokemon(pokemon);
                holder.favoriteIcon.setText("❤️");
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImage;
        TextView pokemonName;
        TextView favoriteIcon;

        ViewHolder(View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemonImage);
            pokemonName = itemView.findViewById(R.id.pokemonName);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
        }
    }
}