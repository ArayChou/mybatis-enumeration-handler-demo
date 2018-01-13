package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

import java.util.HashMap;

/**
 * Created by zhouhongyang@zbj.com on 1/10/2018.
 */
public enum BlogType {
    TOUR(0), TECH(1), OTHER(2);

    private Integer code;


    BlogType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


    private final static HashMap<Integer, BlogType> valueMap = new HashMap<>();

    static {
        for (BlogType o : BlogType.values()) {
            valueMap.put(o.getCode(), o);
        }
    }

    public static BlogType valueOf(Integer siteStatusCode) {
        if (siteStatusCode == null) {
            return null;
        }
        BlogType v = valueMap.get(siteStatusCode);
        if (v == null) {
            throw new RuntimeException("Unkonw Code: " + siteStatusCode);
        }
        return v;
    }
}
