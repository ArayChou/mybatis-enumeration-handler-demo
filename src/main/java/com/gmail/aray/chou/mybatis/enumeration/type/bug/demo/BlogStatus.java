package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

import java.util.HashMap;

/**
 * Created by zhouhongyang@zbj.com on 1/10/2018.
 */
public enum BlogStatus {
    CLOSED(0), HIDDEN(1), NORMAL(2);

    private Integer code;


    BlogStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


    private final static HashMap<Integer, BlogStatus> valueMap = new HashMap<>();

    static {
        for (BlogStatus o : BlogStatus.values()) {
            valueMap.put(o.getCode(), o);
        }
    }

    public static BlogStatus valueOf(Integer siteStatusCode) {
        if (siteStatusCode == null) {
            return null;
        }
        BlogStatus v = valueMap.get(siteStatusCode);
        if (v == null) {
            throw new RuntimeException("Unkonw Code: " + siteStatusCode);
        }
        return v;
    }
}
