/**
 * 
 */
package com.webdemo.weapi.utils;

/**
* Copyright: Copyright (c) 2018
* 
* @ClassName: StringBaseUtils.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: wangweiwen
* @date: 2018年9月30日 下午2:41:20 
*
*/
public class StringBaseUtils {
	
	/**
	 * 利用ACC、ONLINE状态转换为一种状态
	 * @param online
	 * @param acc
	 * @return
	 */
	public static int vehicleState(int online, int acc) {
		
		/*
		车辆在线状态
		(
		1. 离线
		2. 上线
		3. 熄火[在线并熄火告警上来]
		4. 点火[在线并点火告警上来]
		)
		`online_state` int(2) DEFAULT '1' COMMENT '在线状态：1离线，2在线',
		`acc` int(11) DEFAULT '2' COMMENT '车辆ACC：1点火，2熄火',
		 */
		
		int state = 255;
		
		if(1 == online) {	
			state = 1;
			
		}else if((2 == online || 255 == online) && 2 == acc) {		
			state = 3;	
			
		}else if((2 == online || 255 == online) && 1 == acc) {	
			state = 4;	
			
		}else if(2 == online && (2 != acc || 1 != acc || 255 == acc)) {
			state = 2;
		}
		
		return state;
	}
	
	
	
	/**
	 * 
	 * @Description: 判断是否为json字符串
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: wangweiwen
	 * @date: 2018年9月30日 上午11:14:59
	 *
	 */
	public static boolean isJson(String content) {
		boolean flag = false;
		try {
			//JSONObject jsonStr = JSONObject.fromObject(content);
			GsonUtil.getInstance().fromJson(content, Object.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
