/*   
 * Copyright ? 2018 Alibaba. All rights reserved.
 */
package com.aliyun.iot.sign;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具主入口
 * 
 * @author: wy
 * @version: 0.1 2018-11-19 15:06:48
 */
public class 签名工具入口 {

	// 1.需求修改Config.java中的AccessKey信息
	// 2.建议使用方法二，所有参数都需要一一填写Config.java文件中
	// 3."最终signature"才是你需要的签名最终结果
	public static String GetSignature() throws UnsupportedEncodingException {
		String signature=null;
        String 最终地址=null;
        配置文件.Timestamp = 我的工具.getISO8601Time(null);//ISO860格式时间戳
        配置文件.SignatureNonce=我的工具.getUniqueNonce();//唯一随机数。用于防止网络重放攻击。用户在不同请求中要使用不同的随机数值
		Map<String, String> map = new HashMap<String, String>();//构成JSON格式
		// 参数
		map.put("Action", 配置文件.Action);
		map.put("Timestamp", 配置文件.Timestamp);
		map.put("SignatureVersion", 配置文件.SignatureVersion);
		map.put("Format", 配置文件.Format);
		map.put("SignatureNonce", 配置文件.SignatureNonce);
		map.put("Version", 配置文件.Version);
		map.put("AccessKeyId", 配置文件.accessKey);
		map.put("Items", 配置文件.Items);
		map.put("SignatureMethod", 配置文件.SignatureMethod);
		map.put("RegionId",配置文件.RegionId);
		map.put("ProductKey", 配置文件.ProductKey);
		map.put("DeviceName", 配置文件.DeviceName);

		try {
			signature = 签名工具.generate("GET", map, 配置文件.accessKeySecret);//第一个参数表示访问方式，可为GET或POST
			最终地址="https://iot.cn-shanghai.aliyuncs.com/?Action=SetDeviceProperty"
					+"&Signature="+signature
					+"&ProductKey="+配置文件.ProductKey
					+"&DeviceName="+配置文件.DeviceName
					+"&Items="+配置文件.Items
					+"&Format="+配置文件.Format
					+"&Version="+配置文件.Version
					+"&SignatureMethod="+配置文件.SignatureMethod
					+"&SignatureNonce="+配置文件.SignatureNonce
					+"&SignatureVersion="+配置文件.SignatureVersion
					+"&AccessKeyId="+配置文件.accessKey
					+"&Timestamp="+配置文件.Timestamp
					+"&RegionId="+配置文件.RegionId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 最终地址;
	}
}