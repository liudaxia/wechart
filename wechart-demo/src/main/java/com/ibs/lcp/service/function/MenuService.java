package com.ibs.lcp.service.function;

import com.ibs.lcp.model.req.ReqMessage;

/**
 * 菜单功能
 * @author 刘超朋
 *
 */
public interface MenuService {
	
	/**
	 * 主菜单
	 * @param reqMsg
	 * @return
	 */
	public String getMainMenu(ReqMessage reqMsg);
}
