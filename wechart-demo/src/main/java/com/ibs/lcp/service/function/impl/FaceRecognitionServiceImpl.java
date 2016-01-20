package com.ibs.lcp.service.function.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.ibs.lcp.model.function.facerecognition.Face;
import com.ibs.lcp.model.function.facerecognition.FaceCompare;
import com.ibs.lcp.service.function.FaceRecognitionService;
import com.ibs.lcp.util.FaceComment;
import com.ibs.lcp.util.UrlUtil;
@Service
public class FaceRecognitionServiceImpl implements FaceRecognitionService {
	
	

	public String faceRecognition(String picUrl) {
		 // 默认回复信息  
        String result = "未识别到人脸，请换一张清晰的照片再试！";  
        List<Face> faceList = faceDetect(picUrl);  
        if (null != faceList&&faceList.size()>0) {  
            try {
				result = makeMessage(faceList);
			} catch (Exception e) {
				e.printStackTrace();
			}  
        }  
        return result;  
	}
	
	 /** 
     * 调用Face++ API实现人脸检测 
     *  
     * @param picUrl 待检测图片的访问地址 
     * @return List<Face> 人脸列表 
     */  
    private  List<Face> faceDetect(String picUrl) {  
        List<Face> faceList = new ArrayList<Face>();  
        try {  
            // 拼接Face++人脸检测的请求地址  
            String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY";  
            // 对URL进行编码  
            queryUrl = queryUrl.replace("URL", java.net.URLEncoder.encode(picUrl, "UTF-8"));  
            queryUrl = queryUrl.replace("API_KEY", "47ed1bf7d5ceef430334ac8e22176e96");  
            queryUrl = queryUrl.replace("API_SECRET", "Khz_Y8PH6vTR0h50b5B2O8KB_ngA9aZT");  
            // 调用人脸检测接口  
            String json = UrlUtil.httpRequest(queryUrl);  
            // 解析返回json中的Face列表  
            JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("face");  
            // 遍历检测到的人脸  
            for (int i = 0; i < jsonArray.size(); i++) {  
                // face  
                JSONObject faceObject = (JSONObject) jsonArray.get(i);  
                // attribute  
                JSONObject attrObject = faceObject.getJSONObject("attribute");  
                // position  
                JSONObject posObject = faceObject.getJSONObject("position");  
                Face face = new Face();  
                face.setFaceId(faceObject.getString("face_id"));  
                face.setAgeValue(attrObject.getJSONObject("age").getInt("value"));  
                face.setAgeRange(attrObject.getJSONObject("age").getInt("range"));  
                face.setGenderValue(genderConvert(attrObject.getJSONObject("gender").getString("value")));  
                face.setGenderConfidence(attrObject.getJSONObject("gender").getDouble("confidence"));  
                face.setRaceValue(raceConvert(attrObject.getJSONObject("race").getString("value")));  
                face.setRaceConfidence(attrObject.getJSONObject("race").getDouble("confidence"));  
                face.setSmilingValue(attrObject.getJSONObject("smiling").getDouble("value"));  
                face.setCenterX(posObject.getJSONObject("center").getDouble("x"));  
                face.setCenterY(posObject.getJSONObject("center").getDouble("y"));  
                faceList.add(face);  
            }  
            // 将检测出的Face按从左至右的顺序排序  
            Collections.sort(faceList);  
        } catch (Exception e) {  
            faceList = null;  
            e.printStackTrace();  
        }  
        return faceList;  
    }  
  
    /** 
     * 性别转换（英文->中文） 
     *  
     * @param gender 
     * @return 
     */  
    private  String genderConvert(String gender) {  
        String result = "男性";  
        if ("Male".equals(gender))  
            result = "男性";  
        else if ("Female".equals(gender))  
            result = "女性";  
  
        return result;  
    }  
  
    /** 
     * 人种转换（英文->中文） 
     *  
     * @param race 
     * @return 
     */  
    private  String raceConvert(String race) {  
        String result = "黄色";  
        if ("Asian".equals(race))  
            result = "黄色";  
        else if ("White".equals(race))  
            result = "白色";  
        else if ("Black".equals(race))  
            result = "黑色";  
        return result;  
    }  
  
    /** 
     * 根据人脸识别结果组装消息 
     *  
     * @param faceList 人脸列表 
     * @return 
     */  
    private  String makeMessage(List<Face> faceList) throws Exception{  
        StringBuffer buffer = new StringBuffer();  
        // 检测到1张脸  
        if (1 == faceList.size()) {  
            buffer.append("共检测到 ").append(faceList.size()).append(" 张人脸").append("\n");  
            for (Face face : faceList) {  
                buffer.append(face.getRaceValue()).append("人种,");  
                buffer.append(face.getGenderValue()).append(",");  
                buffer.append(face.getAgeValue()).append("岁").append("\n");  
                //大侠脸评
                faceComment(buffer, face);
                
            }  
        }  
        
        // 检测到2张脸  
        if (2 == faceList.size()) {  
            buffer.append("共检测到 ").append(faceList.size()).append(" 张人脸").append("\n");  
            for (int i=0;i<2;i++) {  
            	Face face=faceList.get(i);
            	buffer.append(i==0?"左:":"右:");
                buffer.append(face.getRaceValue()).append("人种,");  
                buffer.append(face.getGenderValue()).append(",");  
                buffer.append(face.getAgeValue()).append("岁").append("\n");  
            }  
            //面部比对
            compareFace(faceList,buffer);
        }  
        
        
        // 检测到3-10张脸  
        else if (faceList.size() > 2 && faceList.size() <= 10) {  
            buffer.append("共检测到 ").append(faceList.size()).append(" 张脸，按脸部中心位置从左至右依次为：").append("\n");  
            for (Face face : faceList) {  
                buffer.append(face.getRaceValue()).append("人种,");  
                buffer.append(face.getGenderValue()).append(",");  
                buffer.append(face.getAgeValue()).append("岁左右").append("\n");  
            }  
        }  
        // 检测到10张脸以上  
        else if (faceList.size() > 10) {  
            buffer.append("共检测到 ").append(faceList.size()).append(" 张人脸").append("\n");  
            // 统计各人种、性别的人数  
            int asiaMale = 0;  
            int asiaFemale = 0;  
            int whiteMale = 0;  
            int whiteFemale = 0;  
            int blackMale = 0;  
            int blackFemale = 0;  
            for (Face face : faceList) {  
                if ("黄色".equals(face.getRaceValue()))  
                    if ("男性".equals(face.getGenderValue()))  
                        asiaMale++;  
                    else  
                        asiaFemale++;  
                else if ("白色".equals(face.getRaceValue()))  
                    if ("男性".equals(face.getGenderValue()))  
                        whiteMale++;  
                    else  
                        whiteFemale++;  
                else if ("黑色".equals(face.getRaceValue()))  
                    if ("男性".equals(face.getGenderValue()))  
                        blackMale++;  
                    else  
                        blackFemale++;  
            }  
            if (0 != asiaMale || 0 != asiaFemale)  
                buffer.append("黄色人种：").append(asiaMale).append("男").append(asiaFemale).append("女").append("\n");  
            if (0 != whiteMale || 0 != whiteFemale)  
                buffer.append("白色人种：").append(whiteMale).append("男").append(whiteFemale).append("女").append("\n");  
            if (0 != blackMale || 0 != blackFemale)  
                buffer.append("黑色人种：").append(blackMale).append("男").append(blackFemale).append("女").append("\n");  
        }  
        // 移除末尾空格  
 //       buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n")));  
        return buffer.toString();  
    }  
    
    
    public void faceComment(StringBuffer buf,Face face){
    	String sex=face.getGenderValue();
    	double sexgrade=face.getGenderConfidence();
    	int age=face.getAgeValue();
    	String race=face.getRaceValue();
    	double smile= face.getSmilingValue();
    	String[] poem={};
    	FaceComment fc=new FaceComment();
    	Random r=new Random();
    	if (sex.equals("女性")&&sexgrade>90&&!race.equals("黑色")&&age>=15&&age<=35) {
    		buf.append("颜评:");
			if (smile<=20) {
    			poem=fc.getCold();
    			buf.append(poem[r.nextInt(poem.length)]);
			}
			if (smile>=80) {
				poem=fc.getSmile();
    			buf.append(poem[r.nextInt(poem.length)]);
			}
			if (smile>20&&smile<80) {
				if (age>=15&&age<=25) {
					poem=fc.getPoem3();
				}else {
					poem=fc.getPoem4();
				}
			}
			buf.append(poem[r.nextInt(poem.length)]);
		}
    }
  
    
    public void compareFace(List<Face> faceList,StringBuffer buf){
    	// 拼接Face++人脸检测的请求地址  
        String queryUrl = "http://apicn.faceplusplus.com/v2/recognition/compare?face_id1=faceId1&face_id2=faceId2&api_secret=API_SECRET&api_key=API_KEY";  
        // 对URL进行编码  
        queryUrl = queryUrl.replace("API_KEY", "47ed1bf7d5ceef430334ac8e22176e96");  
        queryUrl = queryUrl.replace("API_SECRET", "Khz_Y8PH6vTR0h50b5B2O8KB_ngA9aZT"); 
        queryUrl = queryUrl.replace("faceId1", faceList.get(0).getFaceId()); 
        queryUrl = queryUrl.replace("faceId2", faceList.get(1).getFaceId()); 
        // 调用人脸检测接口  
        String json = UrlUtil.httpRequest(queryUrl);  
        // 解析返回json中的Face列表  
        JSONObject jsonObj = JSONObject.fromObject(json);  
        FaceCompare fc=new FaceCompare();
        fc.setSimilarity(jsonObj.getDouble("similarity"));
        fc.setEye(jsonObj.getJSONObject("component_similarity").getDouble("eye"));
        fc.setEyebrow(jsonObj.getJSONObject("component_similarity").getDouble("eyebrow"));
        fc.setMouth(jsonObj.getJSONObject("component_similarity").getDouble("eye"));
        fc.setNose(jsonObj.getJSONObject("component_similarity").getDouble("nose"));
        
        DecimalFormat    df   = new DecimalFormat("######0.00");  
        buf.append("嘴巴相似度:"+df.format(fc.getMouth())).append("\n");
        buf.append("鼻子相似度:"+df.format(fc.getNose())).append("\n");
        buf.append("眼睛相似度:"+df.format(fc.getEye())).append("\n");
        buf.append("眉毛相似度:"+df.format(fc.getEyebrow())).append("\n");
        buf.append("总体相似度:"+df.format(fc.getSimilarity())).append("\n");
        
        Face face1=faceList.get(0);
        Face face2=faceList.get(1);
        //两个男的
        if (face1.getGenderValue().equals("男性")&&face2.getGenderValue().equals("男性")) {
			if (Math.abs(face1.getAgeValue()-face2.getAgeValue())<=10) {
				if (fc.getSimilarity()>60) {
					buf.append("脸评:").append("手足兄弟").append("\n");
				}
				if (fc.getSimilarity()<=60) {
					buf.append("脸评:").append("基情四射").append("\n");
				}
			}else {
				if (fc.getSimilarity()>60) {
					buf.append("脸评:").append("父子情深").append("\n");
				}
			}
		}
        //异性
        if (!face1.getGenderValue().equals(face2.getGenderValue())) {
			if (Math.abs(face1.getAgeValue()-face2.getAgeValue())<=10) {
				if (fc.getSimilarity()>=40) {
					buf.append("脸评:").append("呵呵，愿天下有情人都是失散多年的兄妹或者姐弟/:B-)").append("\n");
				}
				if (fc.getSimilarity()<40) {
					buf.append("脸评:").append("你们画风都不一样，为何会在一起？！！/::d").append("\n");
				}
			}
		}
        if (face1.getGenderValue().equals("女性")&&face2.getGenderValue().equals("女性")&&face1.getAgeValue()<=30&&face2.getAgeValue()<=30){
        	buf.append("放开你旁边那女孩，有什么都冲本大侠呃。。。的主人来/:showlove").append("\n");
        }
        
        
    };

}
