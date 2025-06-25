package me.happycao.lingxi.vo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

/**
 * @author : happyc
 * time    : 2024/09/25
 * desc    :
 * version : 1.0
 */
public abstract class BaseVO {

    @Override
    public String toString() {
        return JSON.toJSONString(this, JSONWriter.Feature.WriteMapNullValue);
    }

}