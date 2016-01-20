package com.ibs.lcp.model.req;

public class ReqMessage {
	/**
	 * 基本信息 
	 */
	// 开发者微信号  
    private String toUserName;  
    // 发送方帐号（一个OpenID）  
    private String fromUserName;    
    // 消息创建时间 （整型）  
    private long createTime;   
    // 消息类型（text/image/location/link）  
    private String msgType;     
    // 消息id，64位整型  
    private long msgId;     
    
    
    /**
     * 文本类请求信息
     */
    //请求内容
    private String content;    
    
    /**
     * 图片类请求信息
     */
    // 图片链接  
    private String picUrl;      
    
    /**
     * 链接类请求信息
     */
    // 消息标题  
    private String title;  
    // 消息描述  
    private String description;  
    // 消息链接  
    private String url;  
    
    /**
     * 地理位置类请求信息
     */
	 // 地理位置维度  
    private String location_X;  
    // 地理位置经度  
    private String location_Y;  
    // 地图缩放大小  
    private String scale;  
    // 地理位置信息  
    private String label;  
    
    /**
     * 语音类请求信息
     */
	 // 媒体ID  
    private String mediaId;  
    // 语音格式  
    private String format;  
  
    
    public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}


	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}  
}
