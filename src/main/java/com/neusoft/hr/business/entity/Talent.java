package com.neusoft.hr.business.entity;



import java.util.Date;

public class Talent extends BaseEntity{

    private String name;

    private String tCode;

    private String sexual;

    private Department department;

    private Position position;

    private Date employDate;

    private Date unemployDate;

    private String status;

    public Talent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getEmployDate() {
        return employDate;
    }

    public void setEmployDate(Date employDate) {
        this.employDate = employDate;
    }

    public Date getUnemployDate() {
        return unemployDate;
    }

    public void setUnemployDate(Date unemployDate) {
        this.unemployDate = unemployDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
