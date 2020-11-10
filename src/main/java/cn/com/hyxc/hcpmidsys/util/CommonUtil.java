package cn.com.hyxc.hcpmidsys.util;

/**
 * 常用工具类
 *
 */
public class CommonUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isNullString(String str){
        String trimStr = str.trim();
        if(trimStr == null | "".equals(trimStr) | trimStr.isEmpty())
        return true;
        else return false;
    }

    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotNullString(String str){
        return !isNullString(str);
    }



}
