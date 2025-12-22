package com.ds.paynote.controller;

import com.ds.paynote.domain.User;
import com.ds.paynote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*
    * 로그인 페이지
    * */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new User());
        return "/user/login";
    }
    
    /*
    * 회원가입 페이지
    * */
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user",new User());
        return "/user/join";
    }
    
    /*
    * 회원가입 기능
    * */
    @PostMapping("/join")
    public String join(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/main";
    }

}
