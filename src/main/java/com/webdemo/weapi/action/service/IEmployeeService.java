package com.webdemo.weapi.action.service;

import com.webdemo.weapi.action.domain.Employee;

public interface IEmployeeService {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String account);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);


    Employee checkLogin(Employee employee);

    Employee checkToken(String token);

    int logout(String token);
}
