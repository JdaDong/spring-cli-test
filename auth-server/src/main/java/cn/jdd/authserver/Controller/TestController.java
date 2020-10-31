package cn.jdd.authserver.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/principal")
    public Principal principal(Principal principal){
        return principal;
    }

//    @RequestMapping("/callback")
//    public String callBack(){}

}
