package com.zhengze.usermanagement.util;

/**
 * @author zhengze
 * @version 1.0
 * @date :2019/9/5 21:24
 */
public class Regex {
    //验证手机号:首位是1，然后是【3.4.5.7.8】，然后是数字
    public static boolean checkPhone(String phone) {
        String regex = "[1][3,4,5,7,8][0-9]{9}";
        boolean matches = phone.matches(regex);
        if (matches) {
            return true;
        }
        return false;
    }

    //验证邮箱
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        boolean matches = email.matches(regex);
        if (matches) {
            return true;
        }
        return false;
    }

    //验证密码
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,12}$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }
}
