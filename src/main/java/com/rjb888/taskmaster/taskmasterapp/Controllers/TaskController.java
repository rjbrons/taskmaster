package com.rjb888.taskmaster.taskmasterapp.Controllers;

import com.rjb888.taskmaster.taskmasterapp.Models.Task;
import com.rjb888.taskmaster.taskmasterapp.Repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    public Iterable<Task> getTask(){
        return taskRepo.findAll();
    }

}
