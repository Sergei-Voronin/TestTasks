package com.example.JiraEmulation.controllers;

import com.example.JiraEmulation.model.Task;
import com.example.JiraEmulation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping(value = "/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return tasks != null &&  !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/task={id}")
    public ResponseEntity<Task> read(@PathVariable Long id) {
        Task task = taskService.getById(id);

        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/task")
    public ResponseEntity<?> create(@RequestBody Task task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/task={id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task task) {
        boolean updated = taskService.update(task, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/task={id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = taskService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
