package cn.com.hyxc.hcpmidsys.modulehttp.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface RequestService {
    public JSONObject ProcessingRequests(HttpServletRequest request, String json);
}
