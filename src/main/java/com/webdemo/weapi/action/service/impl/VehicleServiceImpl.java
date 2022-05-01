package com.webdemo.weapi.action.service.impl;


import com.webdemo.weapi.action.dao.VehicleMapper;
import com.webdemo.weapi.action.domain.ApiConconts;
import com.webdemo.weapi.action.domain.ResponsePageVO;
import com.webdemo.weapi.action.domain.show.app.VehicleInfoShow;
import com.webdemo.weapi.action.service.IVehicleService;
import com.webdemo.weapi.utils.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author xuzhihao
 *
 */
@Slf4j
@Service
public class VehicleServiceImpl implements IVehicleService {

	@Autowired
	private VehicleMapper vehicleMapper;


	@Override
	public ResponsePageVO selectVehicleList(Map<String, Object> param, ResponsePageVO responseData) {

		long count = vehicleMapper.selectVehicleListCount(param);
		System.out.println("*************************" + count + "***************************");
		param = Pager.getParamPage(param, count, Integer.parseInt(param.get(ApiConconts.PAGE_SIZE).toString()));

		List<VehicleInfoShow> resList = vehicleMapper.selectVehicleList(param);

		responseData = responseData.getList(
				responseData, 
				count, 
				resList);

		return responseData;
	}

	@Override
	public long selectVehicleIsExists(Map<String, Object> param) {
		return vehicleMapper.selectVehicleIsExists(param);
	}

	@Override
	public ResponsePageVO selectVehicleSerierList(Map<String, Object> param, ResponsePageVO responseData) {
		return null;
	}

	@Override
	public ResponsePageVO selectVehicleBrandList(Map<String, Object> param, ResponsePageVO responseData) {
		return null;
	}


}
