package com.example.JiraEmulation.service;

import com.example.JiraEmulation.model.Task;
import com.example.JiraEmulation.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void create(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public boolean update(Task task, Long id) {
        if (taskRepository.existsById(id)){
            task.setId(id);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
