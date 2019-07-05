package com.rjb888.taskmaster.taskmasterapp.Controllers;

import com.rjb888.taskmaster.taskmasterapp.Models.Task;
import com.rjb888.taskmaster.taskmasterapp.Repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TaskController {
    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    public Iterable<Task> getTask(){
        return taskRepo.findAll();
    }

    @PostMapping("/tasks")
    public void newTask(String title, String description){
        Task myTask = new Task(title, description);
        taskRepo.save(myTask);
    }

    @PutMapping("/tasks/{id}/state")
    public void updateTask(@PathVariable UUID id){
        Task taskToUpdate = taskRepo.findById(id).get();
        switch (taskToUpdate.getStatus()){
            case "Available":
                taskToUpdate.setStatus("Assigned");
                break;
            case "Assigned":
                taskToUpdate.setStatus("Accepted");
                break;
            case "Accepted":
                taskToUpdate.setStatus("Finished");
                break;
        }
        taskRepo.save(taskToUpdate);
    }
}
