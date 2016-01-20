package com.ibs.lcp.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 检查请求过来的内容类型
 * @author 刘超朋
 *
 */
public class ReqTypeCheck {
    /** 
     * 判断是否是QQ表情 
     *  
     * @param content 
     * @return 
     */  
    public static boolean isQqFace(String content) {  
        boolean result = false;  
      
        // 判断QQ表情的正则表达式  
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";  
        Pattern p = Pattern.compile(qqfaceRegex);  
        Matcher m = p.matcher(content);  
        if (m.matches()) {  
            result = true;  
        }  
        return result;  
    }  
}
