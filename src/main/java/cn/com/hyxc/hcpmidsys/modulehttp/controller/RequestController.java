package cn.com.hyxc.hcpmidsys.modulehttp.controller;

import cn.com.hyxc.hcpmidsys.util.RequestJsonUtil;
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
    @PostMapping( value = "/user/action")
    public void test(HttpServletRequest request, HttpServletResponse response)  {
    String json = RequestJsonUtil.getRequestJsonString(request);
        System.out.println(json);
    }
}
