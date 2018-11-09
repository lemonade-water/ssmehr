package com.neusoft.hr.business.entity;

import java.util.Date;

public class EmlpoyeeTranform extends BaseEntity{
    private String transType;
    private Long inDepartId;
    private Long outDepartId;
    private Long inPosId;
    private Long outPosId;
    private Long tId;
    private Date tranDate;

    public EmlpoyeeTranform() {
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Long getInDepartId() {
        return inDepartId;
    }

    public void setInDepartId(Long inDepartId) {
        this.inDepartId = inDepartId;
    }

    public Long getOutDepartId() {
        return outDepartId;
    }

    public void setOutDepartId(Long outDepartId) {
        this.outDepartId = outDepartId;
    }

    public Long getInPosId() {
        return inPosId;
    }

    public void setInPosId(Long inPosId) {
        this.inPosId = inPosId;
    }

    public Long getOutPosId() {
        return outPosId;
    }

    public void setOutPosId(Long outPosId) {
        this.outPosId = outPosId;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }
}
