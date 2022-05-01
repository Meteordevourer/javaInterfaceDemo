package com.webdemo.weapi.action.service.impl;

import com.webdemo.weapi.action.dao.EmployeeMapper;
import com.webdemo.weapi.action.domain.Employee;
import com.webdemo.weapi.action.service.IEmployeeService;
import com.webdemo.weapi.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Value("${token.time}")
    private int tokenTime;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoderBean() {
//		return new BCryptPasswordEncoder();
//	}


    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Integer empId) {

        return employeeMapper.deleteByPrimaryKey(empId);
    }

    @Override
    public int insert(Employee record) {

        return employeeMapper.insert(record);
    }

    @Override
    public int insertSelective(Employee record) {

        return employeeMapper.insertSelective(record);
    }

    @Override
    public Employee selectByPrimaryKey(String account) {

        return employeeMapper.selectByPrimaryKey(account);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {

        return employeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Employee record) {

        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Employee checkLogin(Employee record) {

        if (null != record) {

            Employee res = employeeMapper.selectByPrimaryKey(record.getAccount());
            if(null != res) {

                BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
                if(encode.matches(record.getPwd(), res.getPwd())) {

                    // 创建token
                    record.setToken(UUID.randomUUID().toString());
                    Date date = new Date();
                    record.setUpdateTime(date);
                    record.setTokenCreateTime(date);
                    record.setTokenValidityTime(DateUtil.addS(date, tokenTime));// 生成过期时间

                    if (0 < employeeMapper.updateUserToken(record)) {

                        return checkToken(record.getToken());
                    }
                }
            }

        }

        return null;
    }

    @Override
    public Employee checkToken(String token) {

        return employeeMapper.checkToken(token);
    }

    @Override
    public int logout(String token) {
        Date date = new Date();
        Employee appUser = new Employee();
        appUser.setToken(token);
        appUser.setTokenValidityTime(DateUtil.reductionS(date,86400));
        appUser.setUpdateTime(date);

        return employeeMapper.logout(appUser);
    }


}
