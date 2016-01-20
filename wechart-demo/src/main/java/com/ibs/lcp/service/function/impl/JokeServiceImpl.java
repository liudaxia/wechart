package com.ibs.lcp.service.function.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.ibs.lcp.model.function.joke.JokeMsg;
import com.ibs.lcp.service.function.JokeService;
import com.ibs.lcp.util.UrlUtil;
@Service
public class JokeServiceImpl implements JokeService {
	
	
	boolean allFlag=false;

	public String getTextJokes() {
		String queryUrl=" http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=pageNum";
		//随机调用一页
		int pageNum=new Random().nextInt(643);
		queryUrl = queryUrl.replace("pageNum", pageNum+"");
		// 调用笑话接口  
        String json = UrlUtil.httpRequest(queryUrl);  
        
        // 解析返回json中的笑话列表  
        JSONArray jsonArray = JSONObject.fromObject(json).getJSONObject("showapi_res_body").getJSONArray("contentlist"); 
        
        List<JokeMsg> jokeList=new ArrayList<JokeMsg>();
        
        Random r=new Random();
        int r1=r.nextInt(jsonArray.size());
        int r2=r.nextInt(jsonArray.size());
        
        for (int i = 0; i < jsonArray.size(); i++) {
        	if (allFlag) {
        		JokeMsg joke=new JokeMsg();
        		JSONObject jokeObject = (JSONObject) jsonArray.get(i);
        		joke.setCt(jokeObject.getString("ct"));
        		joke.setText(jokeObject.getString("text"));
        		joke.setTitle(jokeObject.getString("title"));
        		joke.setType(jokeObject.getString("type"));
        		jokeList.add(joke);
			}
			if (!allFlag&&i==r1||i==r2) {
				JokeMsg joke=new JokeMsg();
        		JSONObject jokeObject = (JSONObject) jsonArray.get(i);
        		joke.setCt(jokeObject.getString("ct"));
        		joke.setText(jokeObject.getString("text"));
        		joke.setTitle(jokeObject.getString("title"));
        		joke.setType(jokeObject.getString("type"));
        		jokeList.add(joke);
			}
		}
        String str=getJokes(jokeList);
		return str;
	}

	private String getJokes( List<JokeMsg> jokeList){
		StringBuffer buf=new StringBuffer();
		for (JokeMsg joke : jokeList) {
			buf.append(joke.getTitle()).append("\n");
			buf.append(joke.getText()).append("\n").append("\n");
		}
		String str=buf.toString();
		return str;
	};
}
