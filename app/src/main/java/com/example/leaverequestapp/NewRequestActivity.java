package com.example.leaverequestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewRequestActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner requestTypeSpinner;
    private EditText commentEditText;
    private Button saveButton;
    private Button closeButton;

    private LeaveRequestDao leaveRequestDao;
    private AppDatabase appDatabase;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);

        requestTypeSpinner = findViewById(R.id.requestTypeSpinner);
        commentEditText = findViewById(R.id.commentEditText);
        saveButton = findViewById(R.id.saveButton);
        closeButton = findViewById(R.id.closeButton);

        TextView startDateText = findViewById(R.id.startDateText);
        TextView endDateText = findViewById(R.id.endDateText);

        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "leave_request_db")
                .build();

        leaveRequestDao = database.leaveRequestDao();

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
                String requestType = requestTypeSpinner.getSelectedItem().toString();
                String comment = commentEditText.getText().toString();
                String startDate = startDateText.getText().toString().replace("Start date: ", "");
                String endDate = endDateText.getText().toString().replace("End date: ", "");
                String userId = ""; // Add the logic to retrieve the user ID

                if (requestType.isEmpty() || comment.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    LeaveRequest leaveRequest = new LeaveRequest(requestType, startDate, endDate, comment, userId, getCurrentDate());

                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            leaveRequestDao.insert(leaveRequest);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Leave request saved", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    });
                }
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and return to MainActivity
            }
        });

        Button dateButton = findViewById(R.id.selectDate);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();

                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        long startDateInMillis = selection.first;
                        long endDateInMillis = selection.second;

                        // Convert the millisecond values to your desired date format
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String startDate = sdf.format(new Date(startDateInMillis));
                        String endDate = sdf.format(new Date(endDateInMillis));

                        startDateText.setText("Start date: " + startDate);
                        endDateText.setText("End date: " + endDate);
                        startDateText.setVisibility(View.VISIBLE);
                        endDateText.setVisibility(View.VISIBLE);

                        Log.d("date", startDate);
                        Log.d("date", endDate);
                    }
                });

                datePicker.show(getSupportFragmentManager(), "DatePicker");
            }
        });


    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
