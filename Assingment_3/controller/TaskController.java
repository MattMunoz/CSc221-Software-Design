package com.example.assignemnt_3.controller;

import com.example.assignemnt_3.Service.TaskService;
import com.example.assignemnt_3.Service.TaskServiceImpl;
import com.example.assignemnt_3.model.Task;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//rest controller to handle requests
@RestController
@RequestMapping("/task")
public class TaskController {

    //creates a task service to manage requests
    private final TaskService taskService;

    public TaskController(TaskServiceImpl taskService){
        this.taskService = taskService;
    }

    //GETS the task
    @GetMapping("/retrieve")
    public Task retrieveTask(@RequestParam String Id) throws IOException {
        return taskService.retrieveTask(Id);
    }

    //POSTS the task
    @PostMapping("/add")
    public void addTask(@RequestBody Task task) throws IOException {
        taskService.addTask(task);
    }

    //PUTS the task
    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable String taskId, @RequestBody String Description) throws IOException {
        taskService.updateTask(taskId, Description);
    }

    //DELETES the task
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable String taskId) throws IOException {
        taskService.deleteTask(taskId);
    }
}
