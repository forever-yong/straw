package cn.tedu.straw.portal.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/user/info")
    public UserDetails info(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("username=" + userDetails.getUsername());
        System.out.println("authorities" + userDetails.getAuthorities());
        return userDetails;
    }
    @GetMapping("/user/add")
    public String add() {
        return "add user";
    }

    @GetMapping("/user/delete")
    public String delete() {
        return "delete user";
    }

    @GetMapping("/user/update")
    public String update() {
        return "update user";
    }

    @GetMapping("/user/list")
    public String list() {
        return "user list";
    }

}