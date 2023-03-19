package com.example.trash_collector;

public class Bin {
    private String binId;
    private String binAddress;
    private String binType;
    private String binCycle;
    private  String binLong;
    private  String binLat;
    private String key;

    public String getKey() {
        return key;
    }

    public Bin() {
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Bin(String binId, String binAddress, String binType, String binCycle, String binLong, String binLat) {
        this.binId = binId;
        this.binAddress = binAddress;
        this.binType = binType;
        this.binCycle = binCycle;
        this.binLong = binLong;
        this.binLat = binLat;
    }

    public Bin(String binAddress, String binType, String binCycle, String binLong, String binLat) {
        this.binAddress = binAddress;
        this.binType = binType;
        this.binCycle = binCycle;
        this.binLong = binLong;
        this.binLat = binLat;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public String getBinAddress() {
        return binAddress;
    }

    public void setBinAddress(String binAddress) {
        this.binAddress = binAddress;
    }

    public String getBinType() {
        return binType;
    }

    public void setBinType(String binType) {
        this.binType = binType;
    }

    public String getBinCycle() {
        return binCycle;
    }

    public void setBinCycle(String binCycle) {
        this.binCycle = binCycle;
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
}
