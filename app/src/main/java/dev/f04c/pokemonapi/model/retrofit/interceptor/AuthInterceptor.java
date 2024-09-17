package dev.f04c.pokemonapi.model.retrofit.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import dev.f04c.pokemonapi.model.retrofit.api.API;
import dev.f04c.pokemonapi.model.dto.response.RefreshTokenDto;
import dev.f04c.pokemonapi.model.retrofit.prefs.AppPreferences;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthInterceptor implements Interceptor {
    private boolean isRefreshing = false;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String url = originalRequest.url().toString();
        String accessToken = AppPreferences.getInstance().getAccessToken();

        // Add token to the request if available
        Request.Builder requestBuilder = originalRequest.newBuilder();
        if (accessToken != null) {
            requestBuilder.header("Authorization", "Bearer " + accessToken);
        }

        // Proceed with the request
        Response response = chain.proceed(requestBuilder.build());

        // If response is unauthorized, try refreshing the token
        if (response.code() == 401 && !isRefreshing) {
            synchronized (this) {
                if (!isRefreshing) {
                    isRefreshing = true;
                    // Refresh token in a separate request
                    refreshToken();
                    // Rebuild the request with the new token
                    String newAccessToken = AppPreferences.getInstance().getAccessToken();
                    requestBuilder.header("Authorization", "Bearer " + newAccessToken);
                    response = chain.proceed(requestBuilder.build());
                    isRefreshing = false;
                }
            }
        }

        return response;
    }

    private void refreshToken() {
        API.userApi().refreshToken().enqueue(new Callback<RefreshTokenDto>() {
            @Override
            public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull retrofit2.Response<RefreshTokenDto> response) {
                if (response.isSuccessful()) {
                    RefreshTokenDto refreshTokenDto = response.body();
                    if (refreshTokenDto != null) {
                        AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());
                    }
                } else {
                    AppPreferences.getInstance().setAccessToken(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RefreshTokenDto> call, @NonNull Throwable t) {
                AppPreferences.getInstance().setAccessToken(null);
            }
        });
    }
}