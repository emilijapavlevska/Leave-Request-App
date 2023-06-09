package com.example.leaverequestapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LeaveRequestDao {
    @Insert
    void insert(LeaveRequest leaveRequest);

    @Query("SELECT * FROM leave_requests")
    List<LeaveRequest> getAllLeaveRequests();
}