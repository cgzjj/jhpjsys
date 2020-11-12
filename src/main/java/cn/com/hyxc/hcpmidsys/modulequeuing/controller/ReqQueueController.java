package cn.com.hyxc.hcpmidsys.modulequeuing.controller;

import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.modulequeuing.service.QueuingService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;

/**
 * 取号机接口交互控制器
 *
 * @author yuanyc
 */
@RestController
public class ReqQueueController {

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
     * @return
     */
    @GetMapping("/queuing/takeno")
    public String queuingTakeno(){

        //假设有人取号
        Queue queuing = new Queue.Builder().setSbkzjsjip("192.168.0.1")
                .setQhxxxlh("2001018888888888000001")
                .setPdh("A001")
                .setYwlb("01")
                .setDlrsfzmhm("511602199501088715")
                .setQhsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()))
                .setRylb("1")
                .setRxbdjg("1")
                .setRxbdxsd("0.98")
                .setJzzply("1")
                .setJzbdzp("xxxxxxx")
                .setXczp("sssssssssssss").build();
        JSONObject jsonObject = queuingService.queuingTakeno(queuing);
        return jsonObject.toJSONString();
    }




}
