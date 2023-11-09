package com.example.assignemnt_3.Service;

import com.example.assignemnt_3.model.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

//implementation service file for easier readability
public interface TaskService {

    void addTask(Task task) throws IOException;
    void updateTask(@PathVariable String taskId, @RequestBody String Description) throws IOException;
    void deleteTask(@PathVariable String taskId) throws IOException;
    Task retrieveTask(String Id) throws IOException;
}
