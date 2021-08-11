package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.dto.Todo;
import com.dinesh.StudentManagementSystem.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestTemplateService {

    @Value("${json_placeholder.base_url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    public User[] getUsers() {
        User[] userResponse =  restTemplate.getForObject(BASE_URL + "/users", User[].class);
        return userResponse;
    }

    public Object[] getPhotos() {
        return restTemplate.getForObject(BASE_URL + "/photos", Object[].class);
    }

    public Todo createTodo(Todo todo) {
//        Todo todo = new Todo(1,1,"Todo 1", true);
        ResponseEntity<Todo> response = restTemplate.postForEntity(BASE_URL + "/photos", todo, Todo.class);
        return response.getBody();
    }

    public Todo updateTodo(Todo todo, int id) {
        System.out.println("todo.getTitle() = " + todo.getTitle());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Todo> entity = new HttpEntity<>(todo,headers);
        String url = String.format("%s/todos/%s",BASE_URL, id);
        System.out.println("url = " + url);
        ResponseEntity<Todo> response = restTemplate.exchange(url, HttpMethod.GET, entity, Todo.class);
        return response.getBody();
    }

    public Todo deleteTodo(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Todo> entity = new HttpEntity<>(headers);
        String url = String.format("%s/todos/%s",BASE_URL, id);
        System.out.println("url = " + url);
        ResponseEntity<Todo> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, Todo.class);
        return response.getBody();
    }
}
