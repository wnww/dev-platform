package com.yhhl.weixin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy;

import com.yhhl.common.MD5Utils;
import com.yhhl.common.RandomUtils;
import com.yhhl.common.ResultBean;
import com.yhhl.common.Sha1Util;
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
	
	private static long timestamp;
	private static String nonceStr;
	
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
		System.out.println("=================这里执行了");
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
			ResultBean<String> resultBean = WebUtil.executeGet(url.toString());
			if(resultBean.getFlag() == 1){ // 正常返回 
				Map<String, Object> result = WebUtil.parseJSON2Map(resultBean.getData());
				if(result.get("access_token")!=null && !"".equals(result.get("access_token"))){
					RETRY_COUNT_ACCESS_TOKEN = 0;
					// 赋值accessTOken
					accessToken = String.valueOf(result.get("access_token"));
					System.out.println("获取accessToken=================="+accessToken);
					obtainJsApiTicket();
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
			ResultBean<String> resultBean = WebUtil.executeGet(url.toString());
			if(resultBean.getFlag() == 1){ // 正常返回 
				Map<String, Object> result = WebUtil.parseJSON2Map(resultBean.getData());
				// errcode：0正常返回，errmsg："ok" 正常返回
				if(((Integer)result.get("errcode"))==0 && "ok".equals(result.get("errmsg")) && !"".equals(result.get("ticket"))){
					RETRY_COUNT_JSAPI_TICKET = 0;
					// 赋值jsApiTicket
					jsApiTicket = String.valueOf(result.get("ticket"));
					System.out.println("获取jsApiTicket=================="+jsApiTicket);
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
		System.out.println("获取jsApiTicket=================="+jsApiTicket);
		sb.append("jsapi_ticket=").append(jsApiTicket);
		setNonceStr(RandomUtils.getRandom(6));
		sb.append("&noncestr=").append(getNonceStr());
		setTimestamp(System.currentTimeMillis()/1000);
		sb.append("&timestamp=").append(getTimestamp());
		sb.append("&url=").append(url);
		String signature = "";
		signature = Sha1Util.getSha1(sb.toString());
		System.out.println("noncestr=============="+getNonceStr());
		System.out.println("timestamp==============="+getTimestamp());
		System.out.println("url================"+url);
		return signature;
	}

	public static String getAppId() {
		return appId;
	}

	public static void setAppId(String appId) {
		AccessTokenUtil.appId = appId;
	}

	public static long getTimestamp() {
		return timestamp;
	}

	public static void setTimestamp(long timestamp) {
		AccessTokenUtil.timestamp = timestamp;
	}

	public static String getNonceStr() {
		return nonceStr;
	}

	public static void setNonceStr(String nonceStr) {
		AccessTokenUtil.nonceStr = nonceStr;
	}

	
	
}
