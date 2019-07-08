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

//    @PostMapping("/tasks")
//    public void newTask(String title, String description, String assignee){
//        if (assignee == null){
//            Task myTask = new Task(title, description);
//            taskRepo.save(myTask);
//        } else {
//            Task myTask = new Task(title, description, assignee);
//            taskRepo.save(myTask);
//        }
//    }

    @GetMapping("/users/{assignee}/tasks")
    public Iterable<Task> getTaskByAssignee(@PathVariable String assignee){
        return taskRepo.findByAssignee(assignee);
    }

    @PostMapping("/tasks")
    public void newTask(@RequestBody Task task){
        if (task.getAssignee() == null){
            task.setStatus("Available");
            taskRepo.save(task);
        } else {
            task.setStatus("Assigned");
            taskRepo.save(task);
        }
    }


//    @PostMapping("/tasks")
//    public void newTask( String title, String description){
//        Task myTask = new Task(title, description);
//        taskRepo.save(myTask);
//    }

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

    @PutMapping("tasks/{id}/assign/{assignee}")
    public void updateAssignee(@PathVariable UUID id, @PathVariable String assignee){
        Task taskToUpdate = taskRepo.findById(id).get();
        taskToUpdate.setStatus("Assigned");
        taskToUpdate.setAssignee(assignee);
        taskRepo.save(taskToUpdate);
    }



}
