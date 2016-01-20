package com.ibs.lcp.model.function.joke;

public class JokeMsg {
	
	/**
	 * 内容
	 */
	private String text;
	
	/**
	 * 上传时间
	 */
	private String ct;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 类型
	 */
	private String type;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
