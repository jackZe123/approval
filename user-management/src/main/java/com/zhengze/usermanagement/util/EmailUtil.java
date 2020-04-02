package com.zhengze.usermanagement.util;



import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

    /**
     * 验证邮箱格式是否合法
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        // 校验途虎邮箱格式
        String reg = "^\\w+([-+.]\\w+)*@tuhu.cn$";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 校验完邮箱格式，拼装到一起，英文分号分隔
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public static String mergeApproverEmail(String str1,String str2,String str3){
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(str1)){
            sb.append(str1);
        }
        if(StringUtils.isNotBlank(str2) && !str2.equals(str1)){
            if(sb.length() <= 0){
                sb.append(str2);
            }else {
                sb.append(";").append(str2);
            }
        }
        if(StringUtils.isNotBlank(str3) && !str3.equals(str1) && !str3.equals(str2)){
            if(sb.length() <= 0){
                sb.append(str3);
            }else {
                sb.append(";").append(str3);
            }
        }
        return sb.toString();
    }
}
