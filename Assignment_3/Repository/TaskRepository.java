package com.example.assignemnt_3.Repository;
import com.example.assignemnt_3.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

//repository to implement the methods
@Repository
public class TaskRepository {


    //method to add tasks to the json file
    public void addTask(Task task) throws IOException {
        //open file
        File file = new File("tasks.json");
        //to manipulate json file
        ObjectMapper mapper = new ObjectMapper();
        //reads the values and stores them in a list
        List<Task> tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});

        //adds a task to the lest
        tasks.add(task);

        //writes the entire list back into the file
        mapper.writeValue(file, tasks);
    }

    //method to delete a task from the json file
    public void deleteTask(String id) throws IOException {
        //open file
        File file = new File("tasks.jason");
        //to manipulate json file
        ObjectMapper mapper = new ObjectMapper();
        //reads the values and stores them in a list
        List<Task> tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});

        //searches for the id we are looking for
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getId().equals(id)){
                tasks.remove(i);
                break;
            }
        }

        //rewrites list of tasks back onto file
        mapper.writeValue(file, tasks);
    }

    //method to retrieve a single task from the json file
    public Task retrieveTask(String id) throws IOException {
        //open file
        File file = new File("tasks.jason");
        //to manipulate json file
        ObjectMapper mapper = new ObjectMapper();
        //reads the values and stores them in a list
        List<Task> tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});

        //creates a null task in case none is found
        Task selected = null;

        //searches for the task to be retrieved
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                selected = task;
                break;
            }
        }

        //returns if found or not
        return selected;
    }

    //method to update a task from json file
    public void updateTask(String id, String newDescription) throws IOException {
        //retrieve id needed
        Task updating = retrieveTask(id);
        //delete the id
        deleteTask(id);

        //updates the task description
        updating.setDescription(newDescription);

        //adds it back into the file
        addTask(updating);
    }
}
