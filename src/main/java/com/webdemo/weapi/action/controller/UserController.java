package com.webdemo.weapi.action.controller;

import com.webdemo.weapi.action.domain.ApiConconts;
import com.webdemo.weapi.action.domain.Employee;
import com.webdemo.weapi.action.domain.ResponseDataListVO;
import com.webdemo.weapi.action.domain.TokenInfo;
import com.webdemo.weapi.action.service.ApiInnerService;
import com.webdemo.weapi.action.service.IEmployeeService;
import com.webdemo.weapi.utils.lang.StringBase;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;



@Slf4j
@RestController
@RequestMapping("/admin")
public class UserController {
    @Resource
    private IEmployeeService employeeService;

    @Resource
    private ApiInnerService apiInnerService;

    /***
     * login
     *
     * @param request  请求数据
     * @param response 相应数据
     */

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseDataListVO login(HttpServletRequest request, HttpServletResponse response){
        ResponseDataListVO responseData = new ResponseDataListVO();
        responseData.setVersion(request.getParameter("version"));
        responseData.setResSrt(request.getParameter("resSrt"));
        String data = request.getParameter("data");

        Map<String,String> params = (Map<String, String>) JSON.parse(data);

        try {
            if(StringBase.checkStr(params.get("account")) && StringBase.checkStr(params.get("password"))){
                Employee employee = new Employee();
                employee.setAccount(params.get("account"));
                employee.setPwd(params.get("password"));
                employee.setPosition(params.get("weCharKey"));

                employee = employeeService.checkLogin(employee);

                if(null != employee){
                    Map<String,Object> data_res = new HashMap<String, Object>();
                    data_res.put("accessToken",employee.getToken());
                    data_res.put("expiresTime",employee.getTokenValidityTime().getTime());
                    data_res.put("id",employee.getEmpId());
                    data_res.put("operatorCode",employee.getOperatorCode());
                    data_res.put("weCharKey",employee.getPosition());

                    responseData.setResCode(ApiConconts.OK.toString());
                    responseData.setData(data_res);
                }else {
                    responseData.setResCode(ApiConconts.USER_ERROR.toString());
                    responseData.setData(ApiConconts.errorInfoMap.get(ApiConconts.USER_ERROR));
                }
            }else {
                responseData.setResCode(ApiConconts.PARAM_EMPTY.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            responseData.setResCode((ApiConconts.INTERNAL_INTERFACE_ABNORMAL.toString()));
        }
        return responseData;
    }
    /***
     * logout
     *
     * @param request  请求数据
     * @param response 响应数据
     */
    @PostMapping(value = "logout")
    public ResponseDataListVO logout(HttpServletRequest request,HttpServletResponse response){
        ResponseDataListVO responseData = new ResponseDataListVO();
        Map<String,String> params = new HashMap<String, String>();
        TokenInfo flag = apiInnerService.handlerParamsData(request,response,params,responseData);

        if(!flag.isCheck()){

            return responseData;
        }
        if (0 < employeeService.logout(params.get(ApiConconts.ACCESS_TOKEN))) {
            responseData.setResCode(ApiConconts.OK.toString());
            responseData.setResMsg("注销成功！");
        }else {
            responseData.setResCode(ApiConconts.ACTION_ERROR.toString());
        }
        return responseData;
    }
}
