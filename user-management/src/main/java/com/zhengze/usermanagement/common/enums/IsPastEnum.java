package com.zhengze.usermanagement.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/13 20:11
 */
public enum IsPastEnum {

    PAST(0,"通过"),
    NOT_PAST(1,"不通过"),
    WAIT_CONFIRM(2,"待审核");

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    IsPastEnum(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public static Map<Integer,String> getIsPartMap(){
        Map<Integer,String> map = new LinkedHashMap<>();
        for (IsPastEnum isPastEnum:values()){
            map.put(isPastEnum.getId(),isPastEnum.getName());
        }
        return map;
    }
}
