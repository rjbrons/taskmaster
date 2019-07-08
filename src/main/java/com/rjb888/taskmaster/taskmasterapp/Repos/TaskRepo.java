package com.rjb888.taskmaster.taskmasterapp.Repos;

import com.rjb888.taskmaster.taskmasterapp.Models.Task;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface TaskRepo extends CrudRepository<Task, String> {
    Optional<Task> findById(UUID id);
    Iterable<Task> findByAssignee(String assignee);
}
