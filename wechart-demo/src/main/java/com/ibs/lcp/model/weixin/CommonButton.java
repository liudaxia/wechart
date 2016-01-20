package com.ibs.lcp.model.weixin;

/**
 * 普通按钮（子按钮） 
 * @author 刘超朋
 *
 */
public class CommonButton extends Button{
	/**
	 * 类型
	 */
	private String type; 
	/**
	 * key值
	 */
    private String key;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }  
}
