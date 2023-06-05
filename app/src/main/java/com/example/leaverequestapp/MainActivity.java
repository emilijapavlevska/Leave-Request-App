package com.example.leaverequestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button newRequestButton;
    private Button historyButton;

    private boolean isSignedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.signInButton);
        newRequestButton = findViewById(R.id.newRequestButton);
        historyButton = findViewById(R.id.historyButton);
    }

    public void onSignInButtonClicked(View view) {
        isSignedIn = !isSignedIn;

        if (isSignedIn) {
            signInButton.setText("Sign Out");
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

    public void onHistoryButtonClicked(View view) {
        // Handle history button click event
    }
}
