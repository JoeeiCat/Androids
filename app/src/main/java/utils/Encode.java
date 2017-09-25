package utils;

import android.util.Base64;

import java.net.URLEncoder;

/**
 * Created by ios12 on 17/9/25.
 * 编码方式转换工具类
 */

public class Encode {
    public static String base64(String content){
        try{
            content = Base64.encodeToString(content.getBytes("utf-8"),Base64.DEFAULT);
            content = URLEncoder.encode(content);   //对字符串进行 URL 编码
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return content;
    }
}
