package com.ibs.lcp.service.function.impl;

import org.springframework.stereotype.Service;

import com.ibs.lcp.service.function.TodayInHistoryService;
import com.ibs.lcp.util.UrlUtil;

@Service
public class TodayInHistoryServiceImpl implements TodayInHistoryService {

	public String getTodayInHistory() {
		 // 获取网页源代码  
        String html = UrlUtil.httpRequest("http://www.rijiben.com/");  
        // 从网页源代码中抽取信息  
        String result = UrlUtil.extract(html);  
  
        return result;  
	}

	
	public static void main(String[] args) {
		 // 获取网页源代码  
        String html = UrlUtil.httpRequest("http://www.rijiben.com/");  
        // 从网页源代码中抽取信息  
        String result = UrlUtil.extract(html);  
  
        System.out.println(result);
	}
}
