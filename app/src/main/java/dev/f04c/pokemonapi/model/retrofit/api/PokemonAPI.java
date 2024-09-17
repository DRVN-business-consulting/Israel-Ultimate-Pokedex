package dev.f04c.pokemonapi.model.retrofit.api;

import java.util.List;

import dev.f04c.pokemonapi.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPI {
    @GET("pokemon")  // Use the correct endpoint
    Call<List<Pokemon>> getPokemons();
}