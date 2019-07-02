package com.rjb888.taskmaster.taskmasterapp.Controllers;

import com.rjb888.taskmaster.taskmasterapp.Models.Task;
import com.rjb888.taskmaster.taskmasterapp.Repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    public Iterable<Task> getTask(){
        return taskRepo.findAll();
    }

    @PostMapping("/tasks")
    public RedirectView newTask(){

        return new RedirectView("/");
    }

    @PutMapping("/tasks/{id}/state")
    public RedirectView updateTask(){
        // if (this.status == "available") ... etc.  update state accordingly

        return new RedirectView("/");
    }
}
