package com.biz.test.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.biz.test.dto.LoginDto;

@Controller 
public class HelloController {
        @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/param")
public String param(@RequestParam("name") String name) {

    System.out.println("name = " + name);

    return "test";
}
@GetMapping("/login-test")
public String loginTest(LoginDto loginDto){
    System.out.println("id = " +loginDto.getId());
    System.out.println("pw = " +loginDto.getPw());

    return "test";
}
}




