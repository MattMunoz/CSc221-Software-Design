package com.example.assignemnt_3.Service;

import com.example.assignemnt_3.Repository.TaskRepository;
import com.example.assignemnt_3.model.Task;
import org.springframework.stereotype.Service;

import java.io.IOException;

//business logic of the application
@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepo;

    public TaskServiceImpl(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public void addTask(Task task) throws IOException {
        taskRepo.addTask(task);
    }

    @Override
    public void updateTask(String taskId, String Description) throws IOException {
        taskRepo.updateTask(taskId, Description);

    }

    @Override
    public void deleteTask(String taskId) throws IOException {
        taskRepo.deleteTask(taskId);
    }

    @Override
    public Task retrieveTask(String Id) throws IOException {
        return taskRepo.retrieveTask(Id);
    }

}
