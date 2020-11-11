package cn.com.hyxc.hcpmidsys.initialize;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.util.XmlQuery;
import javafx.application.Application;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 初始化容器类
 *
 * @author yuanyc
 */

@Component//被spring容器管理
@Order(1) //表示执行顺序
public class ContainerRun implements ApplicationRunner {

    /**
     * 读取 业务/制证计算机的配置xml启动
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ControlComputer> controlComputers = new Vector<ControlComputer>();
        try {
            Document document = new SAXReader().read("src/main/resources/templates/xml/25C10.xml");
            Element element = document.getRootElement();
            List<Element> list  = element.elements();
            for (Element e:
                    list) {
                controlComputers.add(new ControlComputer.Builder()
                        .setCkbh(e.elementText("ckbh"))
                        .setJsjip(e.elementText("jsjip"))
                        .setJsjlb(e.elementText("jsjlb"))
                        .setKbywlb(e.elementText("kbywlb"))
                        .setSbkzjsjbh(e.elementText("sbkzjsjbh"))
                .build());
            }
        }catch ( DocumentException e){
            e.printStackTrace();
        }
        ContainerManager.getContainerManager().putControlComputerList(controlComputers);
    }
}
