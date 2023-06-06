package com.example.leaverequestapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button newRequestButton;
    private Button historyButton;

    private boolean isSignedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);

        signInButton = findViewById(R.id.signInButton);
        newRequestButton = findViewById(R.id.newRequestButton);
        historyButton = findViewById(R.id.historyButton);
    }

    public void onSignInButtonClicked(View view) {
        isSignedIn = !isSignedIn;

        if (isSignedIn) {
            signInButton.setText("Sign Out");
            signin();

            newRequestButton.setVisibility(View.VISIBLE);
            historyButton.setVisibility(View.VISIBLE);
        } else {
            signInButton.setText("Sign In");
            newRequestButton.setVisibility(View.GONE);
            historyButton.setVisibility(View.GONE);
        }
    }

    public void onNewRequestButtonClicked(View view) {
        Intent intent = new Intent(this, NewRequestActivity.class);
        startActivity(intent);
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            (result) -> {
                Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
            });

    public void signin() {
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.FacebookBuilder().build(),
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.AnonymousBuilder().build()))
                .build();

        signInLauncher.launch(signInIntent);
    }

    public void onHistoryButtonClicked(View view) {
        // Handle history button click event
    }
}
