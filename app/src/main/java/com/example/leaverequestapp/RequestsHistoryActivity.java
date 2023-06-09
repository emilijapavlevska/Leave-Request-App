package com.example.leaverequestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RequestsHistoryActivity extends AppCompatActivity {

    private LinearLayout containerLayout;
    private Button closeButton;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_history);

        containerLayout = findViewById(R.id.requestsContainer);
        closeButton = findViewById(R.id.closeButton);

        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "leave_request_db")
                .build();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadRequestsFromDatabase();
    }

    private void loadRequestsFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LeaveRequest> leaveRequests = database.leaveRequestDao().getAllLeaveRequests();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayLeaveRequests(leaveRequests);
                    }
                });
            }
        }).start();
    }

    private void displayLeaveRequests(List<LeaveRequest> leaveRequests) {
        for (LeaveRequest request : leaveRequests) {
            TextView textView = new TextView(this);
            String displayText = "Date Range: " + request.getStartDate() + " to " + request.getEndDate()
                    + "\nRequest Type: " + request.getRequestType();
            textView.setText(displayText);
            containerLayout.addView(textView);
        }
    }

}
