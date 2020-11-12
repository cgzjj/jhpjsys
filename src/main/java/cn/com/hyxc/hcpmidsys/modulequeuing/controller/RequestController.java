package cn.com.hyxc.hcpmidsys.modulequeuing.controller;

import cn.com.hyxc.hcpmidsys.modulequeuing.service.QueuingService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 取号机接口交互控制器
 *
 * @author yuanyc
 */
@RestController
public class RequestController {

    @Autowired
    private QueuingService queuingService;

    /**
     * 取号机启动 发送init
     *
     * @author yuanyc
     */
    @GetMapping("/queuing/init")
    public String queuingInit(){
        /**
         * 验证解析初始化
         *
         *
         */

        JSONObject jsonObject = queuingService.queuingInit();
        return jsonObject.toJSONString();
    }

    /**
     * 取号
     *
     * @author yuanc
     */
    @PostMapping("/queuing/takeno")
    public void queuingTakeno(){

    }




}
