package com.example.assignemnt_3.model;

//Imported Libraries
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

//Implement lombok to automatically create and hide getters and setters
@Getter
@Setter
//class for task
public class Task {

    //three fields required for tasks
    private String id;
    private String description;
    private boolean completed;

    //constructor
    public Task(String id, String description, boolean completed){
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    //method to convert task to json format
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    //method to convert a json format to task
    public static Task fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Task.class);
    }
}
