package com.example.leaverequestapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "leave_requests")
public class LeaveRequest {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "request_type")
    private String requestType;

    @ColumnInfo(name = "from_date")
    private String fromDate;

    @ColumnInfo(name = "to_date")
    private String toDate;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "date_created")
    private String dateCreated;

    // Constructor
    public LeaveRequest(String requestType, String fromDate, String toDate, String comment, String userId, String dateCreated) {
        this.requestType = requestType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.comment = comment;
        this.userId = userId;
        this.dateCreated = dateCreated;
    }

    // Getters and setters (or use public fields)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStartDate() {
        return this.fromDate;
    }

    public String getEndDate() {
        return this.toDate;
    }
}
