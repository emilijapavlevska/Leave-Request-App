package com.example.leaverequestapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LeaveRequest.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LeaveRequestDao leaveRequestDao();
}
