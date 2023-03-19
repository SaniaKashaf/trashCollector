package com.example.trash_collector;

public class HostelData {
    private String hostelName;
    private String hostelAddress;
    private String hostelContactNo;
    private String hostelRent;
    private  String hostelRentOneSeater;
    private  String hostelRentTwoSeater;
    private  String hostelRentThreeSeater;
    private  String hostelRentFourSeater;
    private String hostelImage;
    private String key;
    private String hostelLong;
    private String hostelLat;
    private  String hostelPopularFeature;
    public HostelData(String hostelName, String hostelAddress, String hostelContactNo, String hostelRent, String hostelRentOneSeater, String hostelRentTwoSeater, String hostelRentThreeSeater, String hostelRentFourSeater, String hostelImage, String hostelLong, String hostelLat, String hostelPopularFeature) {
        this.hostelName = hostelName;
        this.hostelAddress = hostelAddress;
        this.hostelContactNo = hostelContactNo;
        this.hostelRent = hostelRent;
        this.hostelRentOneSeater = hostelRentOneSeater;
        this.hostelRentTwoSeater = hostelRentTwoSeater;
        this.hostelRentThreeSeater = hostelRentThreeSeater;
        this.hostelRentFourSeater = hostelRentFourSeater;
        this.hostelImage = hostelImage;
        this.hostelLong = hostelLong;
        this.hostelLat = hostelLat;
        this.hostelPopularFeature = hostelPopularFeature;
    }

    public HostelData() {
    }

    public HostelData(String hostelName, String hostelAddress, String hostelContactNo, String hostelRent, String hostelImage, String hostelLong, String hostelLat) {
        this.hostelName = hostelName;
        this.hostelAddress = hostelAddress;
        this.hostelContactNo = hostelContactNo;
        this.hostelRent = hostelRent;
        this.hostelImage = hostelImage;
        this.hostelLong = hostelLong;
        this.hostelLat = hostelLat;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getHostelAddress() {
        return hostelAddress;
    }

    public void setHostelAddress(String hostelAddress) {
        this.hostelAddress = hostelAddress;
    }

    public String getHostelContactNo() {
        return hostelContactNo;
    }

    public void setHostelContactNo(String hostelContactNo) {
        this.hostelContactNo = hostelContactNo;
    }

    public String getHostelRent() {
        return hostelRent;
    }

    public void setHostelRent(String hostelRent) {
        this.hostelRent = hostelRent;
    }

    public String getHostelRentOneSeater() {
        return hostelRentOneSeater;
    }

    public void setHostelRentOneSeater(String hostelRentOneSeater) {
        this.hostelRentOneSeater = hostelRentOneSeater;
    }

    public String getHostelRentTwoSeater() {
        return hostelRentTwoSeater;
    }

    public void setHostelRentTwoSeater(String hostelRentTwoSeater) {
        this.hostelRentTwoSeater = hostelRentTwoSeater;
    }

    public String getHostelRentThreeSeater() {
        return hostelRentThreeSeater;
    }

    public void setHostelRentThreeSeater(String hostelRentThreeSeater) {
        this.hostelRentThreeSeater = hostelRentThreeSeater;
    }

    public String getHostelRentFourSeater() {
        return hostelRentFourSeater;
    }

    public void setHostelRentFourSeater(String hostelRentFourSeater) {
        this.hostelRentFourSeater = hostelRentFourSeater;
    }

    public String getHostelImage() {
        return hostelImage;
    }

    public void setHostelImage(String hostelImage) {
        this.hostelImage = hostelImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHostelLong() {
        return hostelLong;
    }

    public void setHostelLong(String hostelLong) {
        this.hostelLong = hostelLong;
    }

    public String getHostelLat() {
        return hostelLat;
    }

    public void setHostelLat(String hostelLat) {
        this.hostelLat = hostelLat;
    }

    public String getHostelPopularFeature() {
        return hostelPopularFeature;
    }

    public void setHostelPopularFeature(String hostelPopularFeature) {
        this.hostelPopularFeature = hostelPopularFeature;
    }
}
