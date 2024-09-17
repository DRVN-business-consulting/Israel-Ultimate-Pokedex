package dev.f04c.pokemonapi.model.retrofit.api;

import dev.f04c.pokemonapi.model.SignupRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupAPI {

    @POST("signup")
    Call<Void> signup(@Body SignupRequest signupRequest);
}