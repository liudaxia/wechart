package com.ibs.lcp.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibs.lcp.model.req.ReqMessage;
import com.ibs.lcp.model.resp.RespMessage;
import com.ibs.lcp.service.CoreService;
import com.ibs.lcp.service.function.ArticlesService;
import com.ibs.lcp.service.function.AutoResponse;
import com.ibs.lcp.service.function.ExpressionService;
import com.ibs.lcp.service.function.FaceRecognitionService;
import com.ibs.lcp.service.function.JokeService;
import com.ibs.lcp.service.function.MenuService;
import com.ibs.lcp.service.function.TodayInHistoryService;
import com.ibs.lcp.service.function.TranslateService;
import com.ibs.lcp.service.function.WechartHotArticleService;
import com.ibs.lcp.util.MessageUtil;

 
/** 
 * 核心服务类 
 * @author 刘超朋 
 */  
@Service
public class CoreServiceImpl implements CoreService{  
	
	/**
	 * 日志
	 */
	private static Logger log=Logger.getLogger(CoreServiceImpl.class);
	
	@Autowired
	private ArticlesService articlesServicel;
	
	@Autowired
	private ExpressionService expressionService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private TodayInHistoryService todayInHistoryService;
	
	@Autowired
	private TranslateService translateService;
	
	@Autowired
	private FaceRecognitionService faceRecognitionService;
	
	@Autowired
	private AutoResponse autoResponse;
	
	@Autowired
	private JokeService jokeService;
	
	@Autowired
	private WechartHotArticleService wechartHotArticleService;
	
	/**
	 * 系统关键字
	 */
	private static String[] systemKeyWords={"1","2","3","4","5","7","翻译","?","？","fy","讲笑话","joke","热门:","热门："};
	
	private List<String> mainwords=Arrays.asList(systemKeyWords);
	
	private String daxiaInfo="刘大侠，24,性别男，性格宅，性情侠，算是本大侠的主人兼创造者；无情商，无对象，连恋爱都没有过的三无优质缺爱技术宅;更无耻的是，他说要以女神为模板给我创造一个女侠来要挟本大侠成为他的交友平台，来个女神收了这个缺爱的家伙吧，/::d和他同为光棍简直是本大侠最大的悲哀";
	
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public  String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
            
            log.info("CoreService processRequest params:"+requestMap);
            //封装请求参数
            ReqMessage reqMsg=processReqParams(requestMap);
  
            // 默认返回的文本消息内容  
            String respContent = "呃，本大侠还没有进化成人工智能,请你按如下套路出牌！↓\n"+menuService.getMainMenu(reqMsg); 
            
            // 回复信息
            RespMessage respMsg = new RespMessage();  
            respMsg.setToUserName(reqMsg.getFromUserName());  
            respMsg.setFromUserName(reqMsg.getToUserName());  
            respMsg.setCreateTime(new Date().getTime());  
            respMsg.setFuncFlag(0);  
            respMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            
            
            String content=reqMsg.getContent();
            // 请求内容为文本消息  
            if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	//  请求内容为表情符号时，返回表情
//            	if (ReqTypeCheck.isQqFace(reqMsg.getContent())) {
//            		respContent =expressionService.returnExpression(reqMsg);
//				}
            	 // 请求内容为 "?"时 返回菜单消息
            	if (reqMsg.getContent().equals("?")||reqMsg.getContent().equals("？")) {  
                    respContent =menuService.getMainMenu(reqMsg);  
                } 
                //历史的今天
            	else if (reqMsg.getContent().equals("1")) {
                	respContent =todayInHistoryService.getTodayInHistory();
				}
            	else if (reqMsg.getContent().equals("2")) {
                	respContent ="输入'fy:内容'或者 '翻译:内容'就可以了，比如 输入 'fy:早上好'  或者 翻译:さよなら ,支持 中-英 ，英-中，日-中翻译  ；";
				}
            	else if (reqMsg.getContent().equals("3")) {
                	respContent ="发送一张靓脸图片过来，大侠鉴定一下，/::$颜值高的可以获得隐藏成就哦;";
				}
            	else if (reqMsg.getContent().equals("4")) {
                	respContent ="你难道没发现，本大侠已经快进化成人工智能了么；你想聊点什么？";
				}
            	else if (reqMsg.getContent().equals("5")) {
                	respContent ="请输入本大侠的笑点指令  joke 或者 讲笑话 或者笑话 ，哈哈哈哈哈哈，知道吗？我的腹肌就是靠笑出来的";
				}
            	else if (reqMsg.getContent().equals("6")) {
                	respContent ="输入'热门'即可随机获取微信热门，输入 '热门:关键字' 就可以搜索相关的热文 ，比如输入 '热门:美女',就可以获得美女相关内容";
				}
            	else if (reqMsg.getContent().equals("7")) {
                	respContent =daxiaInfo;
				}
                //翻译
            	else if (content.trim().startsWith("翻译")||content.trim().startsWith("fy")) {
                	String keyWord = content.replaceAll("^翻译", "").replaceAll("^fy", "").trim();  
                	respContent=translateService.translate(keyWord);
				}
                //笑话
            	else if (content.trim().equals("讲笑话")||content.trim().equals("joke")) {
                	respContent=jokeService.getTextJokes();
				}
                //热门
            	else if (content.trim().startsWith("热门：")||content.trim().startsWith("热门:")||content.trim().equals("热门")) {
            		String keyword=content.replaceAll("^热门：", "").replaceAll("^热门:", "").replaceAll("^热门", "").trim(); 
            		wechartHotArticleService.getWechartHotArticle(keyword, respMsg);
				}
                else if(!mainwords.contains(content)){
                	respContent=autoResponse.tulingResponse(reqMsg.getContent());
				}
                
            }  
            
            // 请求内容为图片消息  
            else if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
            	//脸部识别
                respContent = faceRecognitionService.faceRecognition(reqMsg.getPicUrl()); 
            }  
            // 请求内容为地理位置消息  
            else if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            } 
            // 请求内容为链接消息  
            else if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 请求内容为音频消息  
            else if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 请求内容为事件推送  
            else if (reqMsg.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "你好，我是大侠机器人，输入 ？可获得本大侠操作手册，/:,@-D如果想给我加技能，有什么好的建议或者意见可以直接联系我主人";  
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                	//取消订阅后无法再收到消息，所以些什么无所谓啦
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                	// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
                    //历史上的今天
                    if (eventKey.equals("11")) {  
                    	respContent =todayInHistoryService.getTodayInHistory();
                    } 
                    //智能翻译
                    else if (eventKey.equals("12")) {  
                    	respContent ="输入'fy:内容'或者 '翻译:内容'就可以了，比如 输入 'fy:早上好'  或者 翻译:さよなら ,支持 中-英 ，英-中，日-中翻译  ；";
                    } 
                    //人脸识别
                    else if (eventKey.equals("21")) {  
                        respContent = "发送一张靓脸图片过来，大侠鉴定一下，/::$颜值高的可以获得隐藏成就哦;";  
                    } 
                    //大侠陪聊
                    else if (eventKey.equals("22")) {  
                        respContent = "你难道没发现，本大侠已经快进化成人工智能了么；你想聊点什么？";
                    } 
                    //幽默笑话
                    else if (eventKey.equals("23")) {  
                    	respContent=jokeService.getTextJokes();
                    } 
                    //大侠资料
                    else if (eventKey.equals("31")) {  
                        respContent = daxiaInfo;  
                    } 
                    //大侠格言
                    else if (eventKey.equals("32")) {  
                        respContent = "天行健，大侠以自强不息！ --仅以自勉吧";  
                    } 
                    //大侠业务
                    else if (eventKey.equals("33")) {  
                        respContent = "我也不知道我想干嘛，行侠仗义，劫富济贫的行为好像和现在社会不怎么搭调，只能兼职下帮人做公众号，赚点小钱了！";  
                    } 
                    //大侠商城
                    else if (eventKey.equals("34")) {  
                        respContent = "大侠暂时并不具备经商头脑，商城暂时呵呵！";  
                    } 
                }  
            }
            respMsg.setContent(respContent);
            respMessage = MessageUtil.rspMsgToXml(respMsg);  
        } catch (Exception e) {  
            e.printStackTrace();  
            log.error("CoreService processRequest"+e);
        }  
        
        return respMessage;  
    }  
    
    
    
    
    
    public  ReqMessage processReqParams(Map<String, String> requestMap){
    	ReqMessage reqMsg=new ReqMessage();
    	
    	
    	// 发送方帐号（open_id）  
        String fromUserName = requestMap.get("FromUserName");  
        // 公众帐号  
        String toUserName = requestMap.get("ToUserName");  
        // 消息类型  
        String msgType = requestMap.get("MsgType");  
        // 消息内容
        String content = requestMap.get("Content"); 
        
        
        //图片信息
        String picUrl=requestMap.get("PicUrl");
        
        //封装请求消息
        reqMsg.setFromUserName(fromUserName);
        reqMsg.setToUserName(toUserName);
        reqMsg.setMsgType(msgType);
        reqMsg.setContent(content);
        
        //封装图片路径
        reqMsg.setPicUrl(picUrl);
    	return reqMsg;
    }
    
}  


	