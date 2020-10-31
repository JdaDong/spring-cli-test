package cn.jdd.resourceserver.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "login";
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("url", "www.baidu.com");
        return modelAndView;
    }

//    @RequestMapping("/callback")
//    public ResponseEntity callBack(){
//        return new ResponseEntity();
//    }
}
