package cn.com.hyxc.hcpmidsys.modulehttp.service;

import com.alibaba.fastjson.JSONObject;

public interface Handler {
     JSONObject execute(JSONObject data);
}
