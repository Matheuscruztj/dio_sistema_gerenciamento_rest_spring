package one.digitalinnovation.personapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @GetMapping
    public String getBook(){
        return "ola";
    }
}
