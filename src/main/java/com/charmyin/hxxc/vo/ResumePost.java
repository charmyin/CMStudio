package com.charmyin.hxxc.vo;

import java.util.Date;

public class ResumePost {//简历投递
    private String id;//编号

    private Date joinTime;//入职时间

    private String companyId;//公司ID

    private String companyName;//公司名称

    private String positionId;//岗位ID

    private String positionName;//岗位名称

    private Date postTime;//投递时间

    private String salaryMin;//最低薪资

    private String salaryMax;//最高薪资

    private String resumeId;//简历ID

    private String employeeId;//应聘者ID

    private Integer employeeRecordStatus;//应聘者记录状态

    private Integer employerRecordStatus;//雇主公司状态

    private String employeeMessage;//应聘者留言

    private String employerMessage;//雇主留言

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin == null ? null : salaryMin.trim();
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax == null ? null : salaryMax.trim();
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId == null ? null : resumeId.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Integer getEmployeeRecordStatus() {
        return employeeRecordStatus;
    }

    public void setEmployeeRecordStatus(Integer employeeRecordStatus) {
        this.employeeRecordStatus = employeeRecordStatus;
    }

    public Integer getEmployerRecordStatus() {
        return employerRecordStatus;
    }

    public void setEmployerRecordStatus(Integer employerRecordStatus) {
        this.employerRecordStatus = employerRecordStatus;
    }

    public String getEmployeeMessage() {
        return employeeMessage;
    }

    public void setEmployeeMessage(String employeeMessage) {
        this.employeeMessage = employeeMessage == null ? null : employeeMessage.trim();
    }

    public String getEmployerMessage() {
        return employerMessage;
    }

    public void setEmployerMessage(String employerMessage) {
        this.employerMessage = employerMessage == null ? null : employerMessage.trim();
    }
}