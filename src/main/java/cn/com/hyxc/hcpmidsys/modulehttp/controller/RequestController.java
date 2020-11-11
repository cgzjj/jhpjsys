package cn.com.hyxc.hcpmidsys.modulehttp.controller;

import cn.com.hyxc.hcpmidsys.modulehttp.service.RequestService;
import cn.com.hyxc.hcpmidsys.util.RequestJsonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping( value =  "/queue")
    public JSONObject callQequest(HttpServletRequest request){
        String json = RequestJsonUtil.getRequestJsonString(request);

        JSONObject jsonObject = requestService.ProcessingRequests(request,json);

        return jsonObject;
    }
}
