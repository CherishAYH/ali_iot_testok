/*   
 * Copyright ? 2018 Alibaba. All rights reserved.
 */
package com.aliyun.iot.sign;

/**
 * 服务端API签名配置文件
 * 
 * @author: ali
 * @version: 0.1 2018-08-08 08:23:54
 */
public class 配置文件 {

	// AccessKey 信息
	public static String accessKey = "LTAInzum********";
	public static String accessKeySecret = "tyZH1Tz4crzn3g1WZe*********";
	public static String Format= "JSON";
	public static String Version= "2018-01-20";
	public static String SignatureMethod= "HMAC-SHA1";
	public static String Timestamp= "2018-11-19T09:31:19Z";//方式二用
	//public static String Timestamp1= "2018-11-19T09%253A26%253A19Z";//方式一用
	public static String SignatureVersion= "1.0";
	public static String SignatureNonce= "f7a95769-22ff-4c70-b5cd-99eb139b5689";
	public static String RegionId= "cn-shanghai";
	public static String Action=  "SetDeviceProperty";
	public static String DeviceName= "test";
	public static String ProductKey= "a1M8cs*****";
	public static String Items= "{\"Status\":1}";//方式二用
	//public static String Items1= "%257B%2522Status%2522%253A1%257D";//方式一用
	public final static String CHARSET_UTF8 = "utf8";
}