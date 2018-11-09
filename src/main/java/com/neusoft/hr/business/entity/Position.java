package com.neusoft.hr.business.entity;

import java.util.List;

public class Position extends BaseEntity{
    private String posName;
    private String posCode;
    private double posSalary;

    public Position() {
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public double getPosSalary() {
        return posSalary;
    }

    public void setPosSalary(double posSalary) {
        this.posSalary = posSalary;
    }
}
