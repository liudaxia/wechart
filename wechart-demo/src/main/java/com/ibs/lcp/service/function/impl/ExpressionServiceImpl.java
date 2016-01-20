package com.ibs.lcp.service.function.impl;

import org.springframework.stereotype.Service;

import com.ibs.lcp.model.req.ReqMessage;
import com.ibs.lcp.service.function.ExpressionService;

/**
 * 表情回复
 * @author 刘超朋
 *
 */
@Service
public class ExpressionServiceImpl implements ExpressionService{
	
	public String returnExpression(ReqMessage reqMsg){
		String respMsg =reqMsg.getContent()+"发啥表情啊，真是的  ʅ（´◔౪◔）ʃ";
		return respMsg;
	}

}
