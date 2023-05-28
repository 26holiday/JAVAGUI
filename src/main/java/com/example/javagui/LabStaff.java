package com.example.javagui;

public class LabStaff {
    private Employee staffDetails;

    public LabStaff(Employee staffDetails) {
        this.staffDetails = staffDetails;
    }

    public Employee getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(Employee staffDetails) {
        this.staffDetails = staffDetails;
    }
}
