package ie.por.keepitsimple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

@GetMapping("/")
public String sayHello() {
    return "Hello World";
}

}
