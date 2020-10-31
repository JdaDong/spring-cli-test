package cn.jdd.resourceserver.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello..ll";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/poHello")
    @PreAuthorize("hasAuthority('reaikd')")
    public String poHello(String name){
        return "poHello" + name;
    }
}
