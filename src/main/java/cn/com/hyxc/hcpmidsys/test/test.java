package cn.com.hyxc.hcpmidsys.test;

import cn.com.hyxc.hcpmidsys.timer.UpdateContainerManagerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @Autowired
    private UpdateContainerManagerTask updateContainerManagerTask;

    @GetMapping("/timetask")
    public void test() throws Exception {
        updateContainerManagerTask.startCron();
    }
}
