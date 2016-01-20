package com.ibs.lcp.service.function;

import com.ibs.lcp.model.resp.RespMessage;

/**
 * 微信热门精选
 * @author 刘超朋
 *
 */
public interface WechartHotArticleService {

	/**
	 * 获取微信热门鸡汤
	 * @return
	 */
	public RespMessage getWechartHotArticle(String keyword,RespMessage respMsg);
}
