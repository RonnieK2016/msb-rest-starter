package com.udemy.msb.restfulstarter.controllers;
import com.udemy.msb.restfulstarter.domain.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanPath(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
