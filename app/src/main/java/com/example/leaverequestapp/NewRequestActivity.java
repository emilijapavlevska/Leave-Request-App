package com.example.leaverequestapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NewRequestActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner requestTypeSpinner;
    private EditText commentEditText;
    private Button saveButton;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        datePicker = findViewById(R.id.datePicker);
        requestTypeSpinner = findViewById(R.id.requestTypeSpinner);
        commentEditText = findViewById(R.id.commentEditText);
        saveButton = findViewById(R.id.saveButton);
        closeButton = findViewById(R.id.closeButton);

        // Populate the request type spinner with options
        List<String> requestTypes = new ArrayList<>();
        requestTypes.add("Vacation");
        requestTypes.add("Sick Leave");
        requestTypes.add("Maternity Leave");
        requestTypes.add("Marriage Leave");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, requestTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestTypeSpinner.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save button click event
                // Perform the necessary actions to save the request
                finish(); // Close the activity and return to MainActivity
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and return to MainActivity
            }
        });
    }
}
