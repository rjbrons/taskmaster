package com.rjb888.taskmaster.taskmasterapp.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskmasterController {

    @GetMapping("/")
    public String goHome(){
        return "Hello World";
    }
}
