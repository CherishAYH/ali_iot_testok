/*   
 * Copyright ? 2018 Alibaba. All rights reserved.
 */
package com.aliyun.iot.sign;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * URL处理类
 * 
 * @author: ali
 * @version: 0.1 2018-06-21 20:40:52
 */
public class URL符号处理 {

	private final static String CHARSET_UTF8 = "utf8";

	public static String urlEncode(String url) {
		if (!StringUtils.isEmpty(url)) {
			try {
				url = URLEncoder.encode(url, "UTF-8");
			} catch (Exception e) {
				System.out.println("Url encode error:" + e.getMessage());
			}
		}
		return url;
	}

	public static String generateQueryString(Map<String, String> params, boolean isEncodeKV) {//生成签名
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {//遍历所有内容并进行替换
			if (isEncodeKV)
				canonicalizedQueryString.append(percentEncode(entry.getKey())).append("=")
						.append(percentEncode(entry.getValue())).append("&");
			else
				canonicalizedQueryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (canonicalizedQueryString.length() > 1) {
			//canonicalizedQueryString=AccessKeyId=LTAInzumID******&Action=SetDeviceProperty&DeviceName=test&Format=XML&ProductKey=a1M8c******&RegionId=cn-shanghai&SignatureMethod=HMAC-SHA1&SignatureNonce=9bf27ba9-bb05-450c-bece-287c072ba2b4&SignatureVersion=1.0&Timestamp=2018-11-18T03%3A06%3A43Z&Version=2018-01-20&
			canonicalizedQueryString.setLength(canonicalizedQueryString.length() - 1);
		}
		return canonicalizedQueryString.toString();
	}

	public static String percentEncode(String value) {
		try {
			// 使用URLEncoder.encode编码后，将"+","*","%7E"做替换即满足 API规定的编码规范
			return value == null ? null
					: URLEncoder.encode(value, CHARSET_UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E",
							"~");
		} catch (Exception e) {
			
		}
		return "";
	}
}