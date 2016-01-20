package com.ibs.lcp.service.function.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ibs.lcp.model.resp.Article;
import com.ibs.lcp.model.resp.RespMessage;
import com.ibs.lcp.service.function.WechartHotArticleService;
import com.ibs.lcp.util.MessageUtil;
import com.ibs.lcp.util.UrlUtil;
@Service
public class WechartHotArticleServiceImpl implements WechartHotArticleService{

	private String artNum="5";
	int anum=Integer.parseInt(artNum);
    
	public RespMessage getWechartHotArticle(String keyword,RespMessage respMsg) {
		String queryUrl="http://apis.baidu.com/txapi/weixin/wxhot?num=art_num&rand=1&word=key_word&page=1";
		
		if (StringUtils.isEmpty(keyword)) {
			queryUrl=queryUrl.replaceAll("&word=key_word","");
			queryUrl=queryUrl.replaceAll("page=1","page="+new Random().nextInt(7500));
		}else{
			queryUrl=queryUrl.replaceAll("key_word",UrlUtil.urlEncodeUTF8(keyword));
		}
		queryUrl=queryUrl.replace("art_num", artNum);
		// 调用笑话接口  
        String json = UrlUtil.httpRequest(queryUrl);  
             
        List<Article> articleList = new ArrayList<Article>();     
        for (int i = 0; i < anum; i++) {
        	Article art=new Article();
        	JSONObject artObj=JSONObject.fromObject(json).getJSONObject(i+"");  
        	//如果返回条数小于默认条数则不添加进LIST
        	if (!artObj.isEmpty()) {
        		art.setDescription(artObj.getString("description"));
            	art.setPicUrl(artObj.getString("picUrl"));
            	art.setTitle(artObj.getString("title"));
            	art.setUrl(artObj.getString("url"));
            	articleList.add(art);
			}
        	
		}
        respMsg.setArticleCount(articleList.size());  
        respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        respMsg.setArticles(articleList);     
		return respMsg;
	}
	
	public static void main(String[] args) {
		WechartHotArticleServiceImpl s=new WechartHotArticleServiceImpl();
		s.getWechartHotArticle("", new RespMessage());
	}

}
