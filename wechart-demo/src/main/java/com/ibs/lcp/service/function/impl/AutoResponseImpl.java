package com.ibs.lcp.service.function.impl;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.ibs.lcp.service.function.AutoResponse;
import com.ibs.lcp.util.UrlUtil;
@Service
public class AutoResponseImpl implements AutoResponse{

	public String tulingResponse(String info) {
		String queryUrl="http://www.tuling123.com/openapi/api?key=API_KEY&info=reqInfo";
        queryUrl = queryUrl.replace("API_KEY", "f4c6e0922d05a561544564506ad9fede");  
		queryUrl = queryUrl.replace("reqInfo", UrlUtil.urlEncodeUTF8(info));
		// 调用智能答复接口  
        String json = UrlUtil.httpRequest(queryUrl);  
        // 解析返回json
        JSONObject jsonObj = JSONObject.fromObject(json);  
        String rspText=jsonObj.getString("text");
        rspText=rspText.replace("图灵", "大侠");
		return rspText;
	}
}
