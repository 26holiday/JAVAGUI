package com.example.javagui;

import java.util.ArrayList;

public class Lab {
    LabStaff incharge;
    boolean hasProjector;
    ArrayList<Computer> computers;

    public Lab(LabStaff incharge, boolean hasProjector, ArrayList<Computer> computers) {
        this.incharge =incharge;
        this.hasProjector = hasProjector;
        this.computers = computers;
    }

    public LabStaff getIncharge() {
        return incharge;
    }

    public void setIncharge(LabStaff labStaffIncharge) {
        this.incharge = labStaffIncharge;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public ArrayList<Computer> getComputers() {
        return computers;
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }


    public void addComputer(Computer computer)
    {
        computers.add(computer);
    }
    public void removeComputer(Computer computer)
    {
        computers.remove(computer);
    }

    public Computer getComputer(int ComputerID)
    {
        Computer computer2 = null;
        for (Computer computer : computers) {
            int ID = Integer.parseInt(computer.getSystemId());
            if (ID == ComputerID) {
                computer2 = computer;
                return computer;
            }
            else{
                System.out.println("Computer not found");
            }
        }
        return computer2;

    }

    @Override
    public String toString() {
        return "Lab{" +
                "incharge=" + incharge +
                ", hasProjector=" + hasProjector +
                ", computers=" + computers +
                '}';
    }
}
