package com.aliyun.iot.sign;

public class 设备管理 {
    public static void init(String accessKeyID,//初始化程序
                            String accessKeySecret,
                            String SignatureMethod,
                            String SignatureVersion,
                            String Format,
                            String Version,
                            String RegionId)
    {
        配置文件.accessKey=accessKeyID;
        配置文件.accessKeySecret=accessKeySecret;
        配置文件.SignatureMethod=SignatureMethod;
        配置文件.SignatureVersion=SignatureVersion;
        配置文件.Format=Format;
        配置文件.Version=Version;
        配置文件.RegionId=RegionId;
    }

    public static String 设置设备属性(String Action,
                         String DeviceName,
                         String ProductKey,
                         String Items)//设置设备属性并返回结果
    {
        配置文件.Action =Action;
        配置文件.DeviceName=DeviceName;
        配置文件.ProductKey=ProductKey;
        配置文件.Items=Items;
        String GET网址=null;//存储GET网址
        try {
            GET网址=签名工具入口.GetSignature();//生成签名
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GET网址;
    }

}
