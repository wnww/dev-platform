package com.yhhl.weixin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yhhl.common.MD5Utils;
import com.yhhl.common.RandomUtils;
import com.yhhl.common.SpringContextHolder;
import com.yhhl.common.WebUtil;
import com.yhhl.dict.model.Dicts;
import com.yhhl.dict.service.DictsServiceI;

public class AccessTokenUtil {
	
	private final static Logger log = Logger.getLogger(AccessTokenUtil.class);

	// 获取Access_Token 重试计数
	private static int RETRY_COUNT_ACCESS_TOKEN = 0;
	
	// 获取jsapi_ticket 重试计数
	private static int RETRY_COUNT_JSAPI_TICKET = 0;
	
	private static String getAccessTokenUrl;
	private static String grantType;
	private static String appId;
	private static String appSecret;
	private static String getJsApiTicketUrl;
	private static String testToken;
	
	private static String accessToken;
	
	private static String jsApiTicket;
	
	private static DictsServiceI dictsService;
	
	public static void getWeiXinConfig(){
		dictsService = SpringContextHolder.getBean("dictsService");
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("dictTypeName", "weixin");
		List<Dicts> dicts = dictsService.selectByDictTypeName(filterMap);
		for(Dicts dict : dicts){
			if(dict.getDictTypeName().equals("weixin-getAccessTokenUrl")){
				getAccessTokenUrl = dict.getDictValue();
			}
			if(dict.getDictTypeName().equals("weixin-grantType")){
				grantType = dict.getDictValue();
			}
			if(dict.getDictTypeName().equals("weixin-appId")){
				appId = dict.getDictValue();
			}
			if(dict.getDictTypeName().equals("weixin-appSecret")){
				appSecret = dict.getDictValue();
			}
			if(dict.getDictTypeName().equals("weixin-getJsApiTicketUrl")){
				getJsApiTicketUrl = dict.getDictValue();
			}
			if(dict.getDictTypeName().equals("weixin-testToken")){
				testToken = dict.getDictValue();
			}
		}
	}
	
	/**
	 * 获取AccessToken
	 * @return
	 */
	public static String getAccessToken(){
		return accessToken;
	}
	
	/**
	 * 获取jsapi_ticket
	 * @return
	 */
	public static String getJsApiTicket(){
		return jsApiTicket;
	}
	
	public static String getTestToke(){
		return testToken;
	}
	
	
	/**
	 * 获取基础的Access_Token公共方法， 有效期7200秒即两个小时，此方法重试3次，如果3次都获取不到，则停止获取。
	 * </br>
	 * 此方法由定时器自动获取，如果此方法执行3次后仍然无法获取则可以手动调用此方法再次获取
	 * 
	 */
	public static void obtainAccessToken(){
		RETRY_COUNT_ACCESS_TOKEN++;
		if(RETRY_COUNT_ACCESS_TOKEN>=3){
			log.error("==================重试3次后仍然无法获取Access_Token，停止获取");
			return;
		}
		StringBuffer url = new StringBuffer(getAccessTokenUrl);
		url.append("?");
		url.append("grant_type=").append(grantType);
		url.append("&appid=").append(appId);
		url.append("&secret=").append(appSecret);
		try {
			// 调用微信服务，获取Access_Token
			Map<String,Object> map = WebUtil.executeGet(url.toString());
			if("200".equals(map.get("status"))){ // 正常返回 
				Map<String, Object> result = WebUtil.parseJSON2Map(String.valueOf(map.get("result")));
				if(result.get("access_token")!=null && !"".equals(result.get("access_token"))){
					RETRY_COUNT_ACCESS_TOKEN = 0;
					// 赋值accessTOken
					accessToken = String.valueOf(result.get("access_token"));
					log.debug("获取accessToken=================="+accessToken);
					return;
				}else{
					obtainAccessToken(); // 失败后重试
				}
			}else{
				obtainAccessToken(); // 失败后重试
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obtainAccessToken(); // 失败后重试
		}
	}
	
	/**
	 * 获取jsapi_ticket,提供3次重试机会，jsapi_ticket有效期为7200秒即两小时，此方法重试3次，如果3次都获取不到，则停止获取。
	 */
	public static void obtainJsApiTicket(){
		if(accessToken==null || "".equals(accessToken)){
			obtainAccessToken();
		}
		RETRY_COUNT_JSAPI_TICKET++;
		if(RETRY_COUNT_JSAPI_TICKET>=3){
			log.error("==================重试3次后仍然无法获取Access_Token，停止获取");
			return;
		}
		StringBuffer url = new StringBuffer(getJsApiTicketUrl);
		url.append("?");
		url.append("access_token=").append(accessToken);
		url.append("&type=jsapi");
		try {
			Map<String,Object> map = WebUtil.executeGet(url.toString());
			if("200".equals(map.get("status"))){ // 正常返回 
				Map<String, Object> result = WebUtil.parseJSON2Map(String.valueOf(map.get("result")));
				// errcode：0正常返回，errmsg："ok" 正常返回
				if(((Integer)result.get("errcode"))==0 && "ok".equals(result.get("errmsg")) && !"".equals(result.get("ticket"))){
					RETRY_COUNT_JSAPI_TICKET = 0;
					// 赋值jsApiTicket
					jsApiTicket = String.valueOf(result.get("ticket"));
					log.debug("获取jsApiTicket=================="+jsApiTicket);
					return;
				}else{
					obtainJsApiTicket(); // 失败后重试
				}
			}else{
				obtainJsApiTicket(); // 失败后重试
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			obtainJsApiTicket();
		}
	}
	
	/**
	 * 获取 signature 签名
	 * @param 当前页面 url
	 * @return
	 */
	public static String getSignature(String url){
		StringBuffer sb = new StringBuffer();
		sb.append("jsapi_ticket=").append(jsApiTicket);
		sb.append("&noncestr=").append(RandomUtils.generateString(10));
		sb.append("&timestamp=").append(System.currentTimeMillis());
		sb.append("&url=").append(url);
		String signature = "";
		signature = MD5Utils.MD5(sb.toString());
		return signature;
	}
	
}
