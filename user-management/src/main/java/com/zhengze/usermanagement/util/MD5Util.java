package com.zhengze.usermanagement.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author (何戎欣)
 * @version V1.0
 * @ClassName: MD5Util
 * @Description: TODO(公司 : 上海昊沧系统控制技术有限责任公司)
 * @date 2018年7月10日 下午4:50:35
 * @ClassName: MD5Util
 * @return return_type    返回类型
 * @throws
 * @Title: enclosing_method
 */
public class MD5Util {

    public static String getMD5(String input) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_md5;
    }
}
