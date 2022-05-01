package com.webdemo.weapi.action.dao;

import com.webdemo.weapi.action.domain.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String account);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);


    int updateUserToken(Employee record);

    Employee checkToken(String token);

    int logout(Employee appUser);
}
