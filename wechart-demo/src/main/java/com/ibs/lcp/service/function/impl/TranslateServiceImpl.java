package com.ibs.lcp.service.function.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.ibs.lcp.model.function.translate.TranslateResult;
import com.ibs.lcp.service.function.TranslateService;
import com.ibs.lcp.util.UrlUtil;
@Service
public class TranslateServiceImpl implements TranslateService {

	public String translate(String source) {
		String dst = null;  
		  
        // 组装查询地址  
        String requestUrl = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=RHHoDI7v8PuCybQPwLEK1x6H&q=keywords&from=auto&to=auto";  
        // 对参数q的值进行urlEncode utf-8编码 ,将要翻译的文字转化成urlCoding 
        requestUrl = requestUrl.replace("keywords", UrlUtil.urlEncodeUTF8(source));  
  
        // 查询并解析结果  
        try {  
            // 查询并获取返回结果  
            String json = UrlUtil.httpRequest(requestUrl);  
            // 通过Gson工具将json转换成TranslateResult对象  
            TranslateResult translateResult = new Gson().fromJson(json, TranslateResult.class);  
            // 取出translateResult中的译文  
            dst = translateResult.getTrans_result().get(0).getDst();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        if (null == dst)  
            dst = "翻译系统异常，请稍候尝试！";  
        System.out.println("翻译结果:"+dst);
        return dst;  
	}
	
	public static void main(String[] args) {
		
		TranslateServiceImpl c=new TranslateServiceImpl();
		
		String content="翻译    翻译 这个单词";
		
		String keyWord = content.replaceAll("^翻译", "").trim();  
		
		System.out.println(keyWord);
		c.translate(keyWord);//"さよなら"
	}

}
