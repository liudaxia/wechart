package com.ibs.lcp.service;

import javax.servlet.http.HttpServletRequest;

 
/** 
 * 核心服务类 
 * @author 刘超朋 
 */  
public interface CoreService {  
	
	
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public  String processRequest(HttpServletRequest request) ;
    
}  


	