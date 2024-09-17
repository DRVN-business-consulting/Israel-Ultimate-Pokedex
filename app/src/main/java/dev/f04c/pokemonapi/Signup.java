package dev.f04c.pokemonapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import dev.f04c.pokemonapi.model.SignupRequest;
import dev.f04c.pokemonapi.model.retrofit.api.API;
import dev.f04c.pokemonapi.model.retrofit.api.SignupAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextAge;
    private EditText editTextAddress;
    private Button buttonSignup;
    private Button buttonLogin; // Added buttonLogin for the login button
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAge = findViewById(R.id.editTextAge);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSignup = findViewById(R.id.buttonSignup);
        buttonLogin = findViewById(R.id.buttonLogin); // Initialize login button
        progressBar = findViewById(R.id.progressBar);

        // Set click listener for the signup button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignup();
            }
        });

        // Set click listener for the login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void handleSignup() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        int age;

        // Input validation
        try {
            age = Integer.parseInt(editTextAge.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress bar while processing
        progressBar.setVisibility(View.VISIBLE);

        // Create signup request
        SignupRequest signupRequest = new SignupRequest(username, password, name, age, address);
        SignupAPI signupAPI = API.signupApi();  // Directly use signupApi() method
        Call<Void> call = signupAPI.signup(signupRequest);

        // Make API call
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressBar.setVisibility(View.GONE); // Hide progress bar
                if (response.isSuccessful()) {
                    Toast.makeText(Signup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Signup.this, "Signup failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide progress bar
                Toast.makeText(Signup.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}