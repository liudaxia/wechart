package com.ibs.lcp.service.function;

import com.ibs.lcp.model.req.ReqMessage;

/**
 * 表情服务
 * @author 刘超朋
 *
 */
public interface ExpressionService {
	/**
	 * 表情回复
	 * @param reqMsg
	 * @return
	 */
	public String returnExpression(ReqMessage reqMsg);

}
