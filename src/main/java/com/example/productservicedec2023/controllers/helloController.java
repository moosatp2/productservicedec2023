package com.example.productservicedec2023.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class helloController {

    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int times){

        String answer = "";

        for ( int i = 1 ; i <= times ; ++i){
            answer += "Hello " + name + " " + i;
            answer += "<br>";
        }
        return answer;

    }

}
