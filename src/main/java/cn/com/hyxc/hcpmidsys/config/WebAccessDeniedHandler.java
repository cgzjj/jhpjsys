package cn.com.hyxc.hcpmidsys.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException{
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);//403
        resp.setContentType("application/json:charset=utf-8");
        resp.sendRedirect("/403");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"code\":\"403\",\"msg\":\"无权限\"}");
        writer.flush();
        writer.close();
    }
}
