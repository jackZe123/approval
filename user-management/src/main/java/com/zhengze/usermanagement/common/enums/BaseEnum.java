package com.zhengze.usermanagement.common.enums;

import java.io.Serializable;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 14:30
 */
public interface BaseEnum extends Serializable {
    int getCode();

    String getName();

    String getDesc();
}
