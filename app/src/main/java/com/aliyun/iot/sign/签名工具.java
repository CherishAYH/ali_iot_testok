/*   
 * Copyright ? 2018 Alibaba. All rights reserved.
 */
package com.aliyun.iot.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 服务端API签名
 * 
 * @author: ali
 * @version: 0.1 2018-06-21 20:47:05
 */
public class 签名工具 {

	private final static String CHARSET_UTF8 = "utf8";
	private final static String ALGORITHM = "UTF-8";
	private final static String SEPARATOR = "&";

	public static Map<String, String> splitQueryString(String url)
			throws URISyntaxException, UnsupportedEncodingException {
		URI uri = new URI(url);
		String query = uri.getQuery();
		final String[] pairs = query.split("&");
		TreeMap<String, String> queryMap = new TreeMap<String, String>();
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? pair.substring(0, idx) : pair;
			if (!queryMap.containsKey(key)) {
				queryMap.put(key, URLDecoder.decode(pair.substring(idx + 1), CHARSET_UTF8));
			}
		}
		return queryMap;
	}

	public static String generate(String method, Map<String, String> parameter, String accessKeySecret)
			throws Exception {
		String signString = generateSignString(method, parameter);//生成需要签名的字串
		byte[] signBytes = hmacSHA1Signature(accessKeySecret + "&", signString);//获取hmacSHA1Signer格式签名
		//signBytes=[126, -86, 118, 114, 73, 78, -35, -103, -30, -113, -98, -125, -55, 74, -96, 55, -11, -9, 43, -121]
		String signature = newStringByBase64(signBytes);//转换签名内容为文本型
		if ("POST".equals(method))
			return signature;
		return URLEncoder.encode(signature, "UTF-8");
	}
	//计算签名函数
	public static String generateSignString(String httpMethod, Map<String, String> parameter) throws IOException {
		TreeMap<String, String> sortParameter = new TreeMap<String, String>();
		sortParameter.putAll(parameter);
		String canonicalizedQueryString = URL符号处理.generateQueryString(sortParameter, true);
		if (null == httpMethod) {
			throw new RuntimeException("httpMethod can not be empty");
		}
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(httpMethod).append(SEPARATOR);
		stringToSign.append(percentEncode("/")).append(SEPARATOR);
		stringToSign.append(percentEncode(canonicalizedQueryString));
		return stringToSign.toString();
	}

	public static String percentEncode(String value) {
		try {
			return value == null ? null
					: URLEncoder.encode(value, CHARSET_UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E",
							"~");
		} catch (Exception e) {
		}
		return "";
	}

	//以下为签名程序
	public static byte[] hmacSHA1Signature(String secret, String baseString) throws Exception {//hmacSHA1Signature为jar无法跟踪过程
		//secret=tyZH1Tz4crzn3g1WZeuXrv********
		//baseString=GET&%2F&AccessKeyId%3DLTAInzumID******%26Action%3DSetDeviceProperty%26DeviceName%3Dtest%26Format%3DXML%26ProductKey%3Da1M8c******%26RegionId%3Dcn-shanghai%26SignatureMethod%3DHMAC-SHA1%26SignatureNonce%3D9bf27ba9-bb05-450c-bece-287c072ba2b4%26SignatureVersion%3D1.0%26Timestamp%3D2018-11-18T03%3A06%3A43Z%26Version%3D2018-01-20
		//可在此处给个固定值测试
		if (StringUtils.isEmpty(secret)) {//判断密码是否为空
			throw new IOException("secret can not be empty");
		}
		if (StringUtils.isEmpty(baseString)) {//判断baseString是否为空
			return null;
		}
		Mac mac = Mac.getInstance("HmacSHA1");//获取参数
		SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), ALGORITHM);
		mac.init(keySpec);
		return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
	}

	public static String newStringByBase64(byte[] bytes) throws UnsupportedEncodingException {
		//bytes=[-106, -19, 69, -45, -10, -124, -64, -71, -122, 4, -19, -93, 116, -55, -110, 65, 5, -6, -56, 11]
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return new String(Base64.encodeBase64(bytes, false), CHARSET_UTF8);//计算签名
	}
}