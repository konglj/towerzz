package com.sdtower.common.wxmsg;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.sdtower.common.Config;
import com.sdtower.common.util.HttpRequst;
import com.sdtower.common.util.TimeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsDateJsonBeanProcessor;

public class WXCommon {
	//private static final String appid = "wxfcdd680978e98257";
	//private static final String secret = "b2c5f6c16b7e263d9d71ff51fc7d1228";
	
	
    
	
	/**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static Token getToken() {
        Token token = null;
        String requestUrl = WXInfo.token_url.replace("APPID", WXInfo.appid).replace("APPSECRET", WXInfo.secret);
        // 发起GET请求获取凭证
        String result = HttpRequst.sendGet(requestUrl);

        JSONObject jsonObject=JSONObject.fromObject(result);
        
        if (null != jsonObject) {
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
            }
        }
        return token;
    }
    
    private static boolean sendMsg(String jsonData){
    	Token token=new Token();
    	token=getToken();
    	if(token==null)
    		return false;
    	String url=WXInfo.send_url.replace("ACCESS_TOKEN", token.getAccessToken());
    	String sendResult=HttpRequst.sendPost(url, jsonData);
    	JSONObject jsonObject=JSONObject.fromObject(sendResult);
    	if(jsonObject==null){
    		return false;
    	}
    	int code=(Integer)jsonObject.get("errcode");
    	if(code==0)
    		return true;
    	return false;
    	
    	
    }
    
    
    public static boolean sendTaskMsg(int orderid,String touser,String firstStr,String keyword1Str,String keyword2Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.taskTemplateid);
    	String url=String.format(WXInfo.taskUrl, Config.getWebip(),orderid,touser);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
    
    
    public static boolean sendMoneyMsg(String touser,String firstStr,String keyword1Str,String keyword2Str,String keyword3Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.moneyTemplateid);
    	String url=String.format(WXInfo.moneyUrl, Config.getWebip(),touser);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str+"元");
    	Value keyword3=new Value(keyword3Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("keyword3",keyword3);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
    
    
    public static boolean sendShOrderSuccessMsg(int orderid,String touser,String firstStr,String keyword1Str,String keyword2Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.sh_order_successTemplateid);
    	String url=String.format(WXInfo.sh_order_successUrl, Config.getWebip(),orderid,touser);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
    
    public static boolean sendDkApplySuccessMsg(String touser,String firstStr,String keyword1Str,String keyword2Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.dk_apply_successTemplateid);
    	String url=String.format(WXInfo.dk_apply_successUrl, Config.getWebip(),touser);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	keyword2Str=String.format(WXInfo.money_dk_apply_content, keyword2Str);
    	Value keyword2=new Value(keyword2Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }

    public static boolean sendDkSuccessMsg(String txid,String touser,String firstStr,String keyword1Str,String keyword2Str,String keyword3Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.dk_successTemplateid);
    	String url=String.format(WXInfo.dk_successUrl, Config.getWebip(),touser,txid);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str+"元");
    	Value keyword3=new Value(keyword3Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("keyword3",keyword3);
//    	map.put("keyword4",keyword4);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
    
    public static boolean sendTwNoticeMsg(int noticeid,String touser,String firstStr,String keyword1Str,String keyword2Str,String remarkStr){
    	boolean result=false;
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.send_tw_noticeTemplateid);
    	String url=String.format(WXInfo.send_tw_noticeUrl, Config.getSystemip(),noticeid);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
    
    
    public static boolean sendShOrderYQMsg(int orderid,String touser,String firstStr,String keyword1Str,String keyword2Str,String remarkStr){
    	//构造task
    	WxMsg wxMsg=new WxMsg();
    	wxMsg.setTouser(touser);
    	wxMsg.setTemplate_id(WXInfo.sh_order_yqTemplateid);
    	String url=String.format(WXInfo.sh_order_yqUrl, Config.getWebip(),orderid,touser);
    	wxMsg.setUrl(url);
    	wxMsg.setTopcolor(WXInfo.topcolor);
    	Value first=new Value(firstStr);
    	Value keyword1=new Value(keyword1Str);
    	Value keyword2=new Value(keyword2Str);
    	Value remark=new Value(remarkStr);
    	Map<String,Value> map=new HashMap<String,Value>();
    	map.put("first", first);
    	map.put("keyword1", keyword1);
    	map.put("keyword2",keyword2);
    	map.put("remark", remark);
    	wxMsg.setData(map);
    	JSONObject jsonObject=JSONObject.fromObject(wxMsg);
    	if(jsonObject==null){
    		return false;
    	}
    	String json=jsonObject.toString();
    	
    	return sendMsg(json);
    	
    }
}
