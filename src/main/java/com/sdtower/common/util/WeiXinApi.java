package com.sdtower.common.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


public class WeiXinApi {
	// 获取openid URL
	private static String openidUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	private static String userInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
	private static final String appid = "wx3f9dfbd0aafc3a80_11";
	private static final String secret = "60d150728db169c81a65cf677178cf35";

	public static Map getOpenidInfo(String code) {
		Map map = null;
		String openid = "";

		String url = String.format(openidUrl, appid, secret, code);
		String result = "";
		try {
			result = HttpRequst.sendGet(url);

		} catch (Exception e) {
		}
		if (null == result || "".equals(result))
			return map;
		System.out.println(result);
		 map=(Map)JSONObject.fromObject(result);

		return map;
	}
	
	public static Map getUserInfo(String token,String openid){
		Map map=null;
		String url = String.format(userInfoUrl, token, appid);
		String result = "";
		try {
			result = HttpRequst.sendGet(url);

		} catch (Exception e) {
		}
		if (null == result || "".equals(result))
			return map;
		System.out.println(result);
		JSONObject  obj=null;
		try {
			 map=(Map)JSONObject.fromObject(result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return map;
		
		
	}

}
