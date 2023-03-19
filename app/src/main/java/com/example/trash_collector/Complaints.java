package com.example.trash_collector;

public class Complaints {
    private String CompId;
    private String binId;
    private String userId;
    private String binAddress;
    private String userEmail;

    private  String binLong;
    private  String binLat;
    private String key;
    private String complaint;
    private String compStatus;
    private String compKey;

    public Complaints() {
    }


    public Complaints(String compId, String binId, String userId, String binAddress, String userEmail, String binLong, String binLat, String key, String complaint, String compStatus) {
        CompId = compId;
        this.binId = binId;
        this.userId = userId;
        this.binAddress = binAddress;
        this.userEmail = userEmail;
        this.binLong = binLong;
        this.binLat = binLat;
        this.key = key;
        this.complaint = complaint;
        this.compStatus = compStatus;
    }

    public Complaints(String compId, String binId, String userId, String binAddress, String userEmail, String binLong, String binLat, String key, String complaint, String compStatus, String compKey) {
        CompId = compId;
        this.binId = binId;
        this.userId = userId;
        this.binAddress = binAddress;
        this.userEmail = userEmail;
        this.binLong = binLong;
        this.binLat = binLat;
        this.key = key;
        this.complaint = complaint;
        this.compStatus = compStatus;
        this.compKey = compKey;
    }

    public String getCompKey() {
        return compKey;
    }

    public void setCompKey(String compKey) {
        this.compKey = compKey;
    }

    public String getCompId() {
        return CompId;
    }

    public void setCompId(String compId) {
        CompId = compId;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBinAddress() {
        return binAddress;
    }

    public void setBinAddress(String binAddress) {
        this.binAddress = binAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
