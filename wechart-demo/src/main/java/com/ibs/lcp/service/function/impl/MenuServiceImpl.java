package com.ibs.lcp.service.function.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ibs.lcp.model.req.ReqMessage;
import com.ibs.lcp.model.weixin.AccessToken;
import com.ibs.lcp.model.weixin.Button;
import com.ibs.lcp.model.weixin.CommonButton;
import com.ibs.lcp.model.weixin.ComplexButton;
import com.ibs.lcp.model.weixin.Menu;
import com.ibs.lcp.service.function.MenuService;
import com.ibs.lcp.util.WeixinUtil;

/**
 * 菜单功能
 * @author 刘超朋
 *
 */
@Service
public class MenuServiceImpl implements MenuService{
	
	 private static Logger log = Logger.getLogger(MenuServiceImpl.class);  
	  
	 //运行创建菜单	
	 public static void main(String[] args) {  
	        // 第三方用户唯一凭证  
	        String appId = "wx0e33a8f63058868a";  
	        // 第三方用户唯一凭证密钥  
	        String appSecret = "d4624c36b6795d1d99dcf0547af5443d";  
	  
	        // 调用接口获取access_token  
	        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
	  
	        if (null != at) {  
	            // 调用接口创建菜单  
	            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
	  
	            // 判断菜单创建结果  
	            if (0 == result)  
	                log.info("菜单创建成功！");  
	            else  
	                log.info("菜单创建失败，错误码：" + result);  
	        }  
	    }  
	  
	    /** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    private static  Menu getMenu() {  
	        CommonButton btn11 = new CommonButton();  
	        btn11.setName("历史上的今天");  
	        btn11.setType("click");  
	        btn11.setKey("11");  
	  
	        CommonButton btn12 = new CommonButton();  
	        btn12.setName("智能翻译");  
	        btn12.setType("click");  
	        btn12.setKey("12");  
	  
	        CommonButton btn13 = new CommonButton();  
	        btn13.setName("周边搜索");  
	        btn13.setType("click");  
	        btn13.setKey("13");  
	  
	        CommonButton btn14 = new CommonButton();  
	        btn14.setName("历史上的今天");  
	        btn14.setType("click");  
	        btn14.setKey("14");  
	  
	        CommonButton btn21 = new CommonButton();  
	        btn21.setName("人脸识别");  
	        btn21.setType("click");  
	        btn21.setKey("21");  
	  
	        CommonButton btn22 = new CommonButton();  
	        btn22.setName("大侠陪聊");  
	        btn22.setType("click");  
	        btn22.setKey("22");  
	  
	        CommonButton btn23 = new CommonButton();  
	        btn23.setName("幽默笑话");  
	        btn23.setType("click");  
	        btn23.setKey("23");  
	  
	        CommonButton btn24 = new CommonButton();  
	        btn24.setName("人脸识别");  
	        btn24.setType("click");  
	        btn24.setKey("24");  
	  
	        CommonButton btn25 = new CommonButton();  
	        btn25.setName("聊天唠嗑");  
	        btn25.setType("click");  
	        btn25.setKey("25");  
	  
	        CommonButton btn31 = new CommonButton();  
	        btn31.setName("大侠资料");  
	        btn31.setType("click");  
	        btn31.setKey("31");  
	  
	        CommonButton btn32 = new CommonButton();  
	        btn32.setName("大侠格言");  
	        btn32.setType("click");  
	        btn32.setKey("32");  
	  
	        CommonButton btn33 = new CommonButton();  
	        btn33.setName("大侠业务");  
	        btn33.setType("click");  
	        btn33.setKey("33");  
	        
	        CommonButton btn34 = new CommonButton();  
	        btn34.setName("大侠商城");  
	        btn34.setType("click");  
	        btn34.setKey("34"); 
	        
	        
	  
	        ComplexButton mainBtn1 = new ComplexButton();  
	        mainBtn1.setName("生活助手");  
	        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });  
	  
	        ComplexButton mainBtn2 = new ComplexButton();  
	        mainBtn2.setName("休闲驿站");  
	        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23 });  
	  
	        ComplexButton mainBtn3 = new ComplexButton();  
	        mainBtn3.setName("关于大侠");  
	        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 , btn34 });  
	  
	        /** 
	         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
	         *  
	         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
	         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
	         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
	         */  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }  
	
	
	
	
	

	/**
	 * 主菜单
	 * @param reqMsg
	 * @return
	 */
	public String getMainMenu(ReqMessage reqMsg) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，我是大侠机器人，请回复数字选择服务：").append("\n\n");
		buffer.append("1  历史的今天").append("\n");
		buffer.append("2  翻译功能").append("\n");
		buffer.append("3  人脸识别").append("\n");
		buffer.append("4  智能聊天").append("\n");
		buffer.append("5  娱乐笑话").append("\n");
		buffer.append("6  微信热文").append("\n");
		buffer.append("7  关于大侠").append("\n");
		buffer.append("回复“?”显示此帮助菜单");
		return buffer.toString();
	}
}
