package cn.com.hyxc.hcpmidsys.modulehttp.controller;

import cn.com.hyxc.hcpmidsys.modulehttp.service.RequestService;
import cn.com.hyxc.hcpmidsys.util.RequestJsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年11月10日 16:58
 */
@RestController
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping( value = "/user/action")
    public void test(HttpServletRequest request, HttpServletResponse response)  {
        String json = RequestJsonUtil.getRequestJsonString(request);
        System.out.println(json);
    }
    //@CrossOrigin(origins = "http://192.168.101.108:8081")
    @PostMapping( value =  "/queue")
    public JSONObject callQequest(HttpServletRequest request){
        //String json = RequestJsonUtil.getRequestJsonString(request);
        //System.out.println(json);
        JSONObject jsonObject = requestService.ProcessingRequests(request);
        return jsonObject;
    }
}
