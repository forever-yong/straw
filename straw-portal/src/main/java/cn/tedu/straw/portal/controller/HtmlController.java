package cn.tedu.straw.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/index.html")
    public String index(){
        // 返回的字符串是视图名
        // SpringBoot默认配置了前缀是 /templates/，配置配置了后缀是 .html
        // 则“前缀+返回值（视图名）+后缀” = /templates/index.html
    return "index";
    }


    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/register.html")
    public String register(){
        return "register";
    }

    @GetMapping("/question/create.html")
    public String createQuestion(){
        return "question/create";
    }

    @GetMapping("/question/detail.html")
    public String showQuestionDetail(){
        return "question/detail";
    }
}
