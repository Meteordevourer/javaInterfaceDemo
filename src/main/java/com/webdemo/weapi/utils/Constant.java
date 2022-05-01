package com.webdemo.weapi.utils;

import java.text.SimpleDateFormat;

/**
 * 常量
 */
public class Constant {

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final int MASK_LENGTH = 65535;
    
    public static final long SECONDE_PER_DAY = 24 * 60 * 60;

    public static final int REDIS_KEEP_DAY = 3;

    public static final int FORCE_STOP_FLAG = 999999;
 
    public static final String DEVICE_CODE = "deviceCode";
    
    public static final float BATOVL_FLOAT = 0.1F;//电压精度

    public static final int MAX_SPEED_KMPH = 200;//最大平均速度值  
    
    public static final String ALARM_TYPE_LIST_HASH_REDLS = "ALARM_TYPE_LIST_HASH";
    
    public static final String REDIS_OPERATOR_LANGUAGE = "OPERATOR_LANGUAGE";
    
    /*-------------------驾驶评分-------------------------*/
	/**
	 * 驾驶扣分 超速
	 */
	public static final double OVER_SPEED_AVG_NUM = -5.0F;
	/**
	 * 驾驶扣分 怠速
	 */
	public static final double LAZY_SPEED_AVG_NUM = -1.0F;
	/**
	 * 驾驶扣分 急加速
	 */
	public static final double ACCELERATE_AVG_NUM = -1.0F;
	/**
	 * 驾驶扣分 急减速
	 */
	public static final double DECELERATE_AVG_NUM = -5.0F;
	/**
	 * 驾驶扣分 空挡滑行
	 */
	public static final double NEUTRAL_AVG_NUM = -2.0F;
	/**
	 * 驾驶扣分 急转弯
	 */
	public static final double SHARP_TURN_AVG_NUM = -3.0F;
	/**
	 * 驾驶扣分 疲劳驾驶
	 */
	public static final double FATIGUE_AVG_NUM = -3.0F;
    
    
    
    
    
    /*----------------短信参数----------------------*/
    /**
     * 发送成功
     */
    public static final Integer SMS_SUCCESS = 0;

    public static final String TRAVEL_NOTIFY_TOPIC = "wl.travel.notify";
    
    /**
     * 行程处理后的广播事件 -- 创建行程、更新行程、完整行程补录、重算行程
     */
    public final static String TRAVEL_EVENT_TOPIC = "wl.travel.event";

    /**
     * redis中终端信息分组ID
     */
    public final static String DEVICE_INFO_LIST_REDIS = "deviceInfoList";
    
    /**
	 * redis中运营单位分组ID
	 */
	public final static String OPERATOR_INFO_LIST_REDIS = "operatorInfoList";
	/**
	 * redis中faultCode分组ID
	 */
	public final static String VEHICLE_FAULT_INFO_LIST_REDIS = "vehicleFaultInfoList";
	/**
	 * redis中车辆信息分组ID
	 */
	public final static String VEHICLE_INFO_LIST_REDIS = "vehicleInfoList";
	/**
	 * redis中车辆状态分组ID
	 */
	public final static String VEHICLE_STATE_INFO_LIST_REDIS = "vehicleStateInfoList";
	/**
	 * redis中车辆实时行程分组ID
	 */
	public final static String VEHICLE_TRAVEL_REAL_INFO_LIST_REDIS = "vehicleTravelRealTypeList";
	/**
	 * redis中用户基础配置分组ID
	 */
	public final static String USER_BASIC_CONFIG_INFO_LIST_REDIS = "userBasicConfigInfoList";
	
	/**
	 * redis中用户语言分组
	 */
	public final static String USER_LANGUAGE = "USER_LANGUAGE";
	
	/**
	 * redis中用户信息分组ID
	 */
	public final static String USER_INFO_LIST_REDIS = "userList";
	
	/**
	 * apij接口返回消息对象状态位单词
	 */
	public static final Integer RESPONSE_CODE_SUCCESS = 1;
	
	/**
	 * 接口请求返回 --> 参数校验不通过
	 */
	public static final String RESPONSE_PARAM_INVALID = "request param is invalid";
	/**
	 * 接口请求返回 --> 参数校验不通过
	 */
	public static final int RESPONSE_PARAM_ERROR_CODE = 10001;
	/**
	 * 接口请求返回 --> 异常
	 */
	public static final int RESPONSE_ERROR_CODE = 1000;
	
	/**
	 * 行程里程单位转换 m 转 km
	 */
	public final static Float TRAVEL_M_TO_KM = 1000.0F;
	/**
	 * 行程里程单位转换 ml 转 L
	 */
	public final static Float TRAVEL_ML_TO_L = 1000.0F;
	/**
	 * 行程里程单位转换 ms 转 s
	 */
	public final static Float TRAVEL_MS_TO_S = 1000.0F;
	/**
	 * 行程里程单位转换 ms 转 h
	 */
	public final static Float TRAVEL_MS_TO_H = 3600000.0F;
	/**
	 * 行程里程单位转换 ms 转 h
	 */
	public final static Float TRAVEL_S_TO_H = 3600.0F;
	/**
	 * 行程里程单位转换 m/s 转 km/h
	 */
	public final static Float TRAVEL_MS_TO_KMH = 3.6F;
	/**
	 * 行程里程单位转换 m/ms 转 km/h
	 */
	public final static Float TRAVEL_MMS_TO_KMH = 3600.0F;
	
	/**
	 * 行程有效条件 --> 里程 0.05KM
	 */
	public static final Float TRAVEL_CHECK_DRIVING_DISTANCE = 0.05F;
	/**
	 * 行程有效条件 --> 驾驶时间 180s (3分钟)     // 改60s 1分钟
	 */
	public static final Integer TRAVEL_CHECK_DRIVING_TIME = 60;
	
	/**
	 * 车辆行程实时信息--> 实时行程 -->已结束
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_TRAVLE_IS_END = 1;
	/**
	 * 车辆行程实时信息 -->实时行程 -->未结束
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_TRAVLE_NOT_END = 0;
	
	
	/**
	 * 车辆行程实时信息 -->车辆支持OBD
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_IS_OBD = 1;
	/**
	 * 车辆行程实时信息 -->车辆不支持OBD
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_NOT_OBD = 0;
	/**
	 * 车辆行程实时信息 -->是否异常行程-->汇总数据异常
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_IS_ERROR = 1;
	/**
	 * 车辆行程实时信息 -->是否异常行程-->汇总数据正常
	 */
	public final static Integer WL_VEHICLE_TRAVEL_REAL_IS_NOT_ERROR = 0;
	/**
	 * 车辆行程信息 -->行程是否有效-->无效
	 */
	public final static Integer WL_VEHICLE_TRAVEL_IS_PROBLEM = 0;
	/**
	 * 车辆行程信息 -->行程是否有效-->有效
	 */
	public final static Integer WL_VEHICLE_TRAVEL_IS_NOT_PROBLEM = 1;	
	/**
	 * 行程数据 -->汇总成功
	 */
	public final static Integer TRAVEL_IS_SUMMARY_TRUE = 1;
	/**
	 * 行程数据 -->汇总失败
	 */
	public final static Integer TRAVEL_IS_SUMMARY_FALSE = 0;
	
	
	/**
	 * 行程数据 -->行程是否显示 - 显示
	 */
	public final static Integer TRAVEL_IS_SHOW_YES = 1;
	/**
	 * 行程数据 -->行程是否显示 - 不显示
	 */
	public final static Integer TRAVEL_IS_SHOW_NO = 0;
	
	/**
	 * 百公里油耗最大值
	 */
	public static final Double MAX_DRIVING_FUEL = 25.0D;
	
	/**
	 * 重复告警的redis的key值
	 */
	public final static String REPEAT_ALARM_KEY = "REPEAT_ALARM_KEY";

	/**
	 * 告警类型-->AM告警
	 */
	public final static Integer WL_ALARM_TYPE_ALARM_TYPE_AM = 1;
	/**
	 * 告警类型-->RT事件
	 */
	public final static Integer WL_ALARM_TYPE_ALARM_TYPE_RT = 2;
	
	//告警异常
	public final static Integer ALARM_IS_ERROR_YES = 1;
	
	//告警正常
	public final static Integer ALARM_IS_ERROR_NO = 0;
	
	/**
	 * 精确小数2位
	 */
	public static final Integer TWO_DECIMAL_PLACES = 2;
	

	/**
	 * 定位失败 中文
	 */
	public static final String UNABLE_TO_LOCATE_ZH = "无法定位";
	/**
	 * 定位失败 英文
	 */
	public static final String UNABLE_TO_LOCATE_EN = "unable to locate";
	
	/**
	 *告警事件开始和清除缓存
	 */
	public final static String ALARM_INFO_LIST_REDIS = "AlarmInfoList_Redis";

	//###------------------CONN API 接口--------------------
	public final static String CONN_API_OBD_LASTGPS = "obd/lastGps/";

	//###------------------数据表 Redis hash-key --------------------
	public final static String REDIS_HASH_TABLE_DEVICE = "table_device_list";
	
	public final static String REDIS_HASH_TABLE_USER = "table_user_list";
	
	public final static String REDIS_HASH_TABLE_VEHICLE = "table_vehicle_list";
	
	public final static String REDIS_HASH_TABLE_OPERATOR = "table_operator_list";
   /**
	 * 需要推送给用户消息的告警ID
	 */
	public static final String PUSH_ALARM_ID_STR = "300003,600011,300002,700004";
	/**
	 * 车辆设防后需要推送的告警ID
	 */
	public static final String VEHICLE_FORTIFICATION_ALARM_ID = "300003,500002,600011,700001,700004";
	/**
	 * CONN API--时间段内的gps
	 */
	public static final String GET_GPS_DATA = "gps/";
	/**
	 * CONN API--最后一个gps
	 */
	public static final String GET_LAST_GPS_DATA = "gps/last/";
	/**
	 * CONN API--时间段内的全部gps
	 */
	public static final String GET_FIRST_AND_LAST_GPS_DATA = "gps/first/last/";
	/**
	 * CONN API--obd数据对应的速度
	 */
	public static final String GET_OBD_DATA_SPEED = "obd/speed/";
	/**
	 * CONN API--设备列表对应的最后一包obd数据
	 */
	public static final String GET_LAST_OBDS = "obds/last/";
	/**
	 * CONN API--单个设备对应的最后一包obd数据
	 */
	public static final String GET_LAST_OBD = "obd/last/";
	
	public static final String GET_FUEL_OBD_LIST = "obd/";
	
	public static final String GET_FUEL_FIRST_AND_LAST_OBD = "obd/first/last/";
	
	/**
	 * CONN API--gsensor--normal
	 */
	public static final String NORMAL_GSENSOR = "gsensor/normal/";
	/**
	 * CONN API--gsensor--event
	 */
	public static final String EVENT_GSENSOR = "gsensor/event/";

	//###-----------------自定义告警 key --------------------
	public final static int ALARM_FENCE_IN = 500001;//进入电子围栏
	
	public final static int ALARM_FENCE_OUT = 500002;//离开电子围栏
	public final static int ALARM_FAULT = 400001;//DTC

	/**
	 * 发送邮件参数
	 */
	public static final String EMAIL_HTML_EN = "<!doctype html><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\t<head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><title>*|MC:SUBJECT|*</title><style type=\"text/css\">p{margin:10px 0;padding:0;}table{border-collapse:collapse;}h1,h2,h3,h4,h5,h6{display:block;margin:0;padding:0;}img,a img{border:0;height:auto;outline:none;text-decoration:none;}body,#bodyTable,#bodyCell{height:100%;margin:0;padding:0;width:100%;}.mcnPreviewText{display:none !important;}#outlook a{padding:0;}img{-ms-interpolation-mode:bicubic;}table{mso-table-lspace:0pt;mso-table-rspace:0pt;}.ReadMsgBody{width:100%;}.ExternalClass{width:100%;}p,a,li,td,blockquote{mso-line-height-rule:exactly;}a[href^=tel],a[href^=sms]{color:inherit;cursor:default;text-decoration:none;}p,a,li,td,body,table,blockquote{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;}.ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{line-height:100%;}a[x-apple-data-detectors]{color:inherit !important;text-decoration:none !important;font-size:inherit !important;font-family:inherit !important;font-weight:inherit !important;line-height:inherit !important;}#bodyCell{padding:10px;}.templateContainer{max-width:600px !important;}a.mcnButton{display:block;}.mcnImage{vertical-align:bottom;}.mcnTextContent{word-break:break-word;}.mcnTextContent img{height:auto !important;}.mcnDividerBlock{table-layout:fixed !important;}body,#bodyTable{background-color:#FFFFFF;background-image:none;background-repeat:no-repeat;background-position:center;background-size:cover;}#bodyCell{border-top:0;}.templateContainer{border:0;}h1{color:#202020;font-family:Helvetica;font-size:26px;font-style:normal;font-weight:bold;line-height:125%;letter-spacing:normal;text-align:left;}h2{color:#202020;font-family:Helvetica;font-size:22px;font-style:normal;font-weight:bold;line-height:125%;letter-spacing:normal;text-align:left;}h3{color:#202020;font-family:Helvetica;font-size:20px;font-style:normal;font-weight:bold;line-height:125%;letter-spacing:normal;text-align:left;}h4{color:#202020;font-family:Helvetica;font-size:18px;font-style:normal;font-weight:bold;line-height:125%;letter-spacing:normal;text-align:left;}#templateHeader{border-top:0;border-bottom:0;}#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{color:#202020;font-family:Helvetica;font-size:16px;line-height:150%;text-align:left;}#templateHeader .mcnTextContent a,#templateHeader .mcnTextContent p a{color:#2BAADF;font-weight:normal;text-decoration:underline;}#templateBody{border-top:0;border-bottom:0;}#templateBody .mcnTextContent,#templateBody .mcnTextContent p{color:#202020;font-family:Helvetica;font-size:16px;line-height:150%;text-align:left;}#templateBody .mcnTextContent a,#templateBody .mcnTextContent p a{color:#2BAADF;font-weight:normal;text-decoration:underline;}#templateFooter{border-top:0;border-bottom:0;}#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{color:#202020;font-family:Helvetica;font-size:12px;line-height:150%;text-align:left;}#templateFooter .mcnTextContent a,#templateFooter .mcnTextContent p a{color:#202020;font-weight:normal;text-decoration:underline;}\t@media only screen and (min-width:768px){.templateContainer{width:600px !important;}}\t@media only screen and (max-width: 480px){body,table,td,p,a,li,blockquote{-webkit-text-size-adjust:none !important;}}\t@media only screen and (max-width: 480px){body{width:100% !important;min-width:100% !important;}}\t@media only screen and (max-width: 480px){#bodyCell{padding-top:10px !important;}}\t@media only screen and (max-width: 480px){.mcnImage{width:100% !important;}}\t@media only screen and (max-width: 480px){.mcnCartContainer,.mcnCaptionTopContent,.mcnRecContentContainer,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer{max-width:100% !important;width:100% !important;}}\t@media only screen and (max-width: 480px){.mcnBoxedTextContentContainer{min-width:100% !important;}}\t@media only screen and (max-width: 480px){.mcnImageGroupContent{padding:9px !important;}}\t@media only screen and (max-width: 480px){.mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{padding-top:9px !important;}}\t@media only screen and (max-width: 480px){.mcnImageCardTopImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{padding-top:18px !important;}}\t@media only screen and (max-width: 480px){.mcnImageCardBottomImageContent{padding-bottom:9px !important;}}\t@media only screen and (max-width: 480px){.mcnImageGroupBlockInner{padding-top:0 !important;padding-bottom:0 !important;}}\t@media only screen and (max-width: 480px){.mcnImageGroupBlockOuter{padding-top:9px !important;padding-bottom:9px !important;}}\t@media only screen and (max-width: 480px){.mcnTextContent,.mcnBoxedTextContentColumn{padding-right:18px !important;padding-left:18px !important;}}\t@media only screen and (max-width: 480px){.mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{padding-right:18px !important;padding-bottom:0 !important;padding-left:18px !important;}}\t@media only screen and (max-width: 480px){.mcpreview-image-uploader{display:none !important;width:100% !important;}}\t@media only screen and (max-width: 480px){h1{font-size:22px !important;line-height:125% !important;}}\t@media only screen and (max-width: 480px){h2{font-size:20px !important;line-height:125% !important;}}\t@media only screen and (max-width: 480px){h3{font-size:18px !important;line-height:125% !important;}}\t@media only screen and (max-width: 480px){h4{font-size:16px !important;line-height:150% !important;}}\t@media only screen and (max-width: 480px){table.mcnBoxedTextContentContainer td.mcnTextContent,td.mcnBoxedTextContentContainer td.mcnTextContent p{font-size:14px !important;line-height:150% !important;}}\t@media only screen and (max-width: 480px){td#templateHeader td.mcnTextContent,td#templateHeader td.mcnTextContent p{font-size:16px !important;line-height:150% !important;}}\t@media only screen and (max-width: 480px){td#templateBody td.mcnTextContent,td#templateBody td.mcnTextContent p{font-size:16px !important;line-height:150% !important;}}\t@media only screen and (max-width: 480px){td#templateFooter td.mcnTextContent,td#templateFooter td.mcnTextContent p{font-size:14px !important;line-height:150% !important;}}</style></head><body><span class=\"mcnPreviewText\" style=\"display:none; font-size:0px; line-height:0px; max-height:0px; max-width:0px; opacity:0; overflow:hidden; visibility:hidden; mso-hide:all;\">*|MC_PREVIEW_TEXT|*</span><center><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\"><tr><td align=\"left\" valign=\"top\" id=\"bodyCell\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\"><tr><td valign=\"top\" id=\"templateHeader\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\"><tbody class=\"mcnTextBlockOuter\"><tr><td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\"><table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\"><tbody><tr><td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\"><h1><img data-file-id=\"495957\" height=\"37\" src=\"https://gallery.mailchimp.com/75320af934274804cf283fa0c/images/73b029fc-e7ca-483e-8cb7-da66fddf330f.png\" style=\"border: 0px  ; width: 150px; height: 37px; margin: 0px;\" width=\"150\"></h1><hr></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td valign=\"top\" id=\"templateBody\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\"><tbody class=\"mcnTextBlockOuter\"><tr><td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\"><table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\"><tbody><tr><td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\"> Dear USRNAME：<br><br>&nbsp; &nbsp; &nbsp; I am glad to hear from you. It's my pleasure to help you.<br>&nbsp; &nbsp; &nbsp; CONTENT <br>&nbsp;<br>Magicyo<br>ZTEWelink Technology Co.,Ltd.<hr></td></tr></tbody></table></td></tr></tbody></table></td></tr><tr><td valign=\"top\" id=\"templateFooter\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\"><tbody class=\"mcnTextBlockOuter\"><tr><td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\"><table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\"><tbody><tr><td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\"><div style=\"text-align: center;\"><span style=\"font-size:12px\">If you have any questions, please <a href=\"mailto:magicyo@gosuncn.com\" target=\"_blank\">contact us</a></span></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></table></td></tr></table></center></body></html>";
;
}

