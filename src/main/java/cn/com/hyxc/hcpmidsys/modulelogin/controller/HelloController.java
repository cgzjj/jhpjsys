package cn.com.hyxc.hcpmidsys.modulelogin.controller;
import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年09月23日 15:06
 */
@RestController
public class HelloController {

    @Autowired
    private ContainerManager containerManager;

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","Hello SpringBoot!");
        return "test";
    }

//    @RequestMapping({"/","popupsignin"})//mvc扩展中已经实现
//    public String popupsignin(){
//        return "popupsignin";
//    }

    public String testGit(){
        return "Git111!";
    }

    /**
     * 测试函数1
     *
     * @author: lily 2020-9-25
     * @param
     * @return
     */
    public String testGit2(){
        //验证

        


        return "git";
    }


   /* @GetMapping
    public String login(){
        return "login_page";
    }*/


    public String testGit3(){
        return "git1";
    }


    @GetMapping("/admin/hello")
    public String hello2(){
        List<ControlComputer> controlComputers = containerManager.getControlComputers();
        System.out.println(controlComputers);

        ControlComputer computer = controlComputers.get(0);
        containerManager.updateComputerStatus(computer.getUuid(),"1");
        System.out.println(containerManager.getControlComputers());
        return "admin";
    }

    @GetMapping("/db/hello")
    public String hello3(){

        return "db";
    }

   /* @GetMapping("/user/hello")
    public ModelAndView hello4(){
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        ModelAndView m = new ModelAndView("user");
        return m;
    }*/


    @RequestMapping("/user/hello2")
    public ModelAndView hello5(){
        System.out.println(1/0);
        ModelAndView modelAndView = new ModelAndView("user");
        return modelAndView;
    }







}
