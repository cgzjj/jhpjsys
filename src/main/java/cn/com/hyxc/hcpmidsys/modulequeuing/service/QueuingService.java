package cn.com.hyxc.hcpmidsys.modulequeuing.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public interface QueuingService {

    JSONObject queuingInit();
}
