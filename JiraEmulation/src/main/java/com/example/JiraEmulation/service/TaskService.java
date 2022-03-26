package com.example.JiraEmulation.service;

import com.example.JiraEmulation.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    void create(Task task);
    Task getById(Long id);
    boolean update (Task task, Long id);
    boolean delete (Long id);
}
