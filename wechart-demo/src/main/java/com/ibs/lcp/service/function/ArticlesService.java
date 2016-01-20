package com.ibs.lcp.service.function;

import com.ibs.lcp.model.req.ReqMessage;
import com.ibs.lcp.model.resp.RespMessage;

/**
 * 图文信息回复
 * @author 刘超朋
 */
public interface ArticlesService {

	/**
	 * 图文回复
	 * @param reqMsg
	 * @param respMsg
	 * @return
	 */
	public RespMessage getArticles(ReqMessage reqMsg,RespMessage respMsg);
	
}
