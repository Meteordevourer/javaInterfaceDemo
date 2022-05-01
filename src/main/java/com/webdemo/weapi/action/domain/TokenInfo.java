package com.webdemo.weapi.action.domain;

public class TokenInfo {
    private boolean check;
    private Employee employee;

    public boolean isCheck(){
        return check;
    }
    public void setCheck(boolean check){
        this.check = check;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
