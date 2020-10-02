package cn.jdd.springcenter.Controller;


import Annotation.LoggerAnnotation;
import Log.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RequestMapping("/test")
@RestController
public class TestController {
    private static Logger logger = Logger.getLogger(TestController.class.getName());

    private static LogFactory logFactory = new LogFactory(TestController.class);

    @LoggerAnnotation
    @RequestMapping("/hello")
    public String testHello(){
        logFactory.debugLog("enter hello");
        return "hello";
    }
}
