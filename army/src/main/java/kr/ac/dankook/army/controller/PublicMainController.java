package kr.ac.dankook.army.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicMainController {

    @RequestMapping("/main")
    public String mainPage(){ return "page/main.html";}
    @RequestMapping("/login")
    public String loginPage(){
        return "page/member/login.html";
    }
    @RequestMapping("/signup")
    public String signUpPage(){
        return "page/member/signup.html";
    }
    @RequestMapping("/findId")
    public String findIdPage(){
        return "page/member/findId.html";
    }
    @RequestMapping("/findPassword")
    public String findPasswordPage(){
        return "page/member/findPassword.html";
    }
}
