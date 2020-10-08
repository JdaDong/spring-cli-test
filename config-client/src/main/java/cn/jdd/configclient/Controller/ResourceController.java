package cn.jdd.configclient.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Value("${user.username}")
    String userName;

    @Value("${user.password}")
    String passWord;

    @RequestMapping("/get")
    public String getResource(){
        StringBuilder stringBuilder = new StringBuilder("username");
        stringBuilder.append(userName).append("<br>").append("password").append(passWord);
        return stringBuilder.toString();
    }
}
