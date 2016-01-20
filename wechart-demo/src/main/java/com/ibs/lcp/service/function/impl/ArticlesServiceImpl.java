package com.ibs.lcp.service.function.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibs.lcp.model.req.ReqMessage;
import com.ibs.lcp.model.resp.Article;
import com.ibs.lcp.model.resp.RespMessage;
import com.ibs.lcp.service.function.ArticlesService;
import com.ibs.lcp.util.MessageUtil;

/**
 * 图文信息回复
 * @author 刘超朋
 */
@Service
public class ArticlesServiceImpl implements ArticlesService{

	public RespMessage getArticles(ReqMessage reqMsg,RespMessage respMsg){
		List<Article> articleList = new ArrayList<Article>();               
        // 图文消息
        if (reqMsg.getContent().equals("1")) { 
        	
        	 Article article = new Article();  
             article.setTitle("微信公众帐号开发教程Java版");  
             article.setDescription("刘超朋，90后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");  
             article.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
             article.setUrl("http://blog.csdn.net/lyq8479");  
             articleList.add(article);  
             respMsg.setArticleCount(articleList.size());  
             respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
             respMsg.setArticles(articleList);  
             
        } 
        // 单图文消息---不含图片  
        else if ("2".equals(reqMsg.getContent())) {  
            Article article = new Article();  
            article.setTitle("微信公众帐号开发教程Java版");  
            // 图文消息中可以使用QQ表情、符号表情  
            article.setDescription("刘超朋，90后，" + emoji(0x1F6B9)  
                    + "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");  
            // 将图片置为空  
            article.setPicUrl("");  
            article.setUrl("http://blog.csdn.net/lyq8479");  
            articleList.add(article); 
            respMsg.setArticleCount(articleList.size());  
            respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            respMsg.setArticles(articleList);  
        }  
        // 多图文消息  
        else if ("3".equals(reqMsg.getContent())) {  
            Article article1 = new Article();  
            article1.setTitle("微信公众帐号开发教程\n引言");  
            article1.setDescription("");  
            article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
            article1.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");  

            Article article2 = new Article();  
            article2.setTitle("第2篇\n微信公众帐号的类型");  
            article2.setDescription("");  
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");  

            Article article3 = new Article();  
            article3.setTitle("第3篇\n开发模式启用及接口配置");  
            article3.setDescription("");  
            article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");  

            articleList.add(article1);  
            articleList.add(article2);  
            articleList.add(article3); 
            respMsg.setArticleCount(articleList.size());  
            respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            respMsg.setArticles(articleList);  
        }  
        // 多图文消息---首条消息不含图片  
        else if ("4".equals(reqMsg.getContent())) {  
            Article article1 = new Article();  
            article1.setTitle("微信公众帐号开发教程Java版");  
            article1.setDescription("");  
            // 将图片置为空  
            article1.setPicUrl("");  
            article1.setUrl("http://blog.csdn.net/lyq8479");  

            Article article2 = new Article();  
            article2.setTitle("第4篇\n消息及消息处理工具的封装");  
            article2.setDescription("");  
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");  

            Article article3 = new Article();  
            article3.setTitle("第5篇\n各种消息的接收与响应");  
            article3.setDescription("");  
            article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");  

            Article article4 = new Article();  
            article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");  
            article4.setDescription("");  
            article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");  

            articleList.add(article1);  
            articleList.add(article2);  
            articleList.add(article3);  
            articleList.add(article4);  
            respMsg.setArticleCount(articleList.size());  
            respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            respMsg.setArticles(articleList);  
        }  
        // 多图文消息---最后一条消息不含图片  
        else if ("5".equals(reqMsg.getContent())) {  
            Article article1 = new Article();  
            article1.setTitle("第7篇\n文本消息中换行符的使用");  
            article1.setDescription("");  
            article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");  
            article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");  

            Article article2 = new Article();  
            article2.setTitle("第8篇\n文本消息中使用网页超链接");  
            article2.setDescription("");  
            article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");  
            article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");  

            Article article3 = new Article();  
            article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");  
            article3.setDescription("");  
            // 将图片置为空  
            article3.setPicUrl("");  
            article3.setUrl("http://blog.csdn.net/lyq8479");  

            articleList.add(article1);  
            articleList.add(article2);  
            articleList.add(article3);  
            respMsg.setArticleCount(articleList.size());  
            respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            respMsg.setArticles(articleList);  
        }  
        
        
        return respMsg;
	} 
	
	 /** 
     * emoji表情转换(hex -> utf-16) 
     *  
     * @param hexEmoji 
     * @return 
     */  
    public static String emoji(int hexEmoji) {  
        return String.valueOf(Character.toChars(hexEmoji));  
    }
}
