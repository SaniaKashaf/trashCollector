package com.example.trash_collector;

public class DriverInfo {
    public String empID;
public String empKey;
    public String name;
    public String phoneno;
    public String empMail;
    public String empAddress;
    public String empPassword;
    public DriverInfo(){
    }

    public DriverInfo(String name, String phoneno, String empMail, String empAddress, String empPassword) {
        this.name = name;
        this.phoneno = phoneno;
        this.empMail = empMail;
        this.empAddress = empAddress;
        this.empPassword = empPassword;
    }

    public DriverInfo(String empID, String name, String phoneno, String empMail, String empAddress, String empPassword) {
        this.empID = empID;
        this.name = name;
        this.phoneno = phoneno;
        this.empMail = empMail;
        this.empAddress = empAddress;
        this.empPassword = empPassword;
    }

    public DriverInfo(String empID, String empKey, String name, String phoneno, String empMail, String empAddress, String empPassword) {
        this.empID = empID;
        this.empKey = empKey;
        this.name = name;
        this.phoneno = phoneno;
        this.empMail = empMail;
        this.empAddress = empAddress;
        this.empPassword = empPassword;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpKey() {
        return empKey;
    }

    public void setEmpKey(String empKey) {
        this.empKey = empKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }
}

