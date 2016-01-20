package com.ibs.lcp.model.weixin;
/**
 * 复杂按钮
 * @author 刘超朋
 *
 */
public class ComplexButton extends Button{
	private Button[] sub_button;  
	  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}
