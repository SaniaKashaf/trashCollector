package com.example.trash_collector;

public class Tasks {
    private String taskID;
    private String CompId;
    private String CompKey;
    private String binId;
    private String userKey;
    private String binAddress;
    private  String binLong;
    private  String binLat;
    private String TaskKey;
    private String complaint;
    private String compStatus;
    private String assignedDriverKey;
    private String DriverId;
    private String DriverName;

    public Tasks() {
    }

    public Tasks(String taskID, String compId, String compKey, String binId, String userKey, String binAddress, String binLong, String binLat, String complaint, String compStatus, String assignedDriverKey, String driverId, String driverName) {
        this.taskID = taskID;
        CompId = compId;
        CompKey = compKey;
        this.binId = binId;
        this.userKey = userKey;
        this.binAddress = binAddress;
        this.binLong = binLong;
        this.binLat = binLat;
        this.complaint = complaint;
        this.compStatus = compStatus;
        this.assignedDriverKey = assignedDriverKey;
        DriverId = driverId;
        DriverName = driverName;
    }

    public Tasks(String taskID, String compId, String compKey, String binId, String userKey, String binAddress, String binLong, String binLat, String taskKey, String complaint, String compStatus, String assignedDriverKey, String driverId, String driverName) {
        this.taskID = taskID;
        CompId = compId;
        CompKey = compKey;
        this.binId = binId;
        this.userKey = userKey;
        this.binAddress = binAddress;
        this.binLong = binLong;
        this.binLat = binLat;
        TaskKey = taskKey;
        this.complaint = complaint;
        this.compStatus = compStatus;
        this.assignedDriverKey = assignedDriverKey;
        DriverId = driverId;
        DriverName = driverName;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getCompId() {
        return CompId;
    }

    public void setCompId(String compId) {
        CompId = compId;
    }

    public String getCompKey() {
        return CompKey;
    }

    public void setCompKey(String compKey) {
        CompKey = compKey;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getBinAddress() {
        return binAddress;
    }

    public void setBinAddress(String binAddress) {
        this.binAddress = binAddress;
    }

    public String getBinLong() {
        return binLong;
    }

    public void setBinLong(String binLong) {
        this.binLong = binLong;
    }

    public String getBinLat() {
        return binLat;
    }

    public void setBinLat(String binLat) {
        this.binLat = binLat;
    }

    public String getTaskKey() {
        return TaskKey;
    }

    public void setTaskKey(String taskKey) {
        TaskKey = taskKey;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getCompStatus() {
        return compStatus;
    }

    public void setCompStatus(String compStatus) {
        this.compStatus = compStatus;
    }

    public String getAssignedDriverKey() {
        return assignedDriverKey;
    }

    public void setAssignedDriverKey(String assignedDriverKey) {
        this.assignedDriverKey = assignedDriverKey;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }
}
