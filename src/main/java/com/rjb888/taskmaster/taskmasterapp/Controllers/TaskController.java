package com.rjb888.taskmaster.taskmasterapp.Controllers;

import com.rjb888.taskmaster.taskmasterapp.Models.Task;
import com.rjb888.taskmaster.taskmasterapp.Repos.S3Client;
import com.rjb888.taskmaster.taskmasterapp.Repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@CrossOrigin
@RestController
public class TaskController {

    private S3Client s3Client;

    @Autowired
    TaskController (S3Client s3Client){
        this.s3Client = s3Client;
    }

    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    public Iterable<Task> getTask(){
        return taskRepo.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable UUID id){
        return taskRepo.findById(id).get();
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
    public Task newTask(@RequestBody Task task){
        if (task.getAssignee() == null){
            task.setStatus("Available");
            taskRepo.save(task);
        } else {
            task.setStatus("Assigned");
            taskRepo.save(task);
        }
        return task;
    }

    @PostMapping("/tasks/{id}/images")
    public Task addTaskImage(@PathVariable UUID id, @RequestPart MultipartFile file){
        Task taskToUpdate = taskRepo.findById(id).get();
        taskToUpdate.setImage(this.s3Client.uploadFile(file));
        taskRepo.save(taskToUpdate);
        return taskToUpdate;
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
