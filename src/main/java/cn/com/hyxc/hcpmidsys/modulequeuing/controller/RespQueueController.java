package cn.com.hyxc.hcpmidsys.modulequeuing.controller;

import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.container.QueueListener;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;

import java.util.Vector;

import static cn.com.hyxc.hcpmidsys.util.HttpUtil.sendHttp;

/**
 * 取号机回应控制器
 *
 * @author yuanyc
 */
@Controller
public class RespQueueController implements QueueListener {

    /**
     * 此方法监听到数量并返回给取号机，作为一个重要监听器
     *
     * @author yuanyc
     */
    @Override
    public void onUpdate(Vector<Queue> queue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("size",queue.size());
        String url = "http://192.168.0.1:8080/ss";
        System.out.println("返回给取号机,当前等待人数 : "+queue.size());
        //sendHttp(url,jsonObject.toJSONString());
    }
}
