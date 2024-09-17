package dev.f04c.pokemonapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import dev.f04c.pokemonapi.model.dto.request.LoginDto;
import dev.f04c.pokemonapi.model.dto.response.LoginResponse;
import dev.f04c.pokemonapi.model.retrofit.prefs.AppPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {

    private LoginDto correctCredentials;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        String urlString = "http://192.169.14.109:9090/login";

        EditText usernameInput = findViewById(R.id.editTextUsername);
        EditText passwordInput = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            fetchCredentials(urlString, username, password);
        });
    }

    private void fetchCredentials(String urlString, String username, String password) {
        executorService.execute(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create JSON payload
                String payload = new Gson().toJson(new LoginDto(username, password));

                // Write payload to output stream
                OutputStream os = connection.getOutputStream();
                os.write(payload.getBytes());
                os.flush();
                os.close();

                // Get response code
                int responseCode = connection.getResponseCode();
                Log.d("Login", "Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    // Parse JSON response
                    Gson gson = new Gson();
                    LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);

                    // Save the access token
                    AppPreferences.getInstance().setAccessToken(loginResponse.getAccessToken());

                    handler.post(() -> {
//                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                        // Start PokemonList activity
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();  // Finish the Login activity
                    });

                } else {
                    handler.post(() -> Toast.makeText(Login.this, "Failed to login: " + responseCode, Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                Log.e("F04CLogin", "Exception: ", e);  // Log the exception
                handler.post(() -> Toast.makeText(Login.this, "Failed to login", Toast.LENGTH_SHORT).show());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        });
    }





    // Method to check if the credentials are correct using LoginDto
    private boolean authenticate(LoginDto loginDto) {
        return loginDto.getUsername().equals(correctCredentials.getUsername()) &&
                loginDto.getPassword().equals(correctCredentials.getPassword());
    }
}