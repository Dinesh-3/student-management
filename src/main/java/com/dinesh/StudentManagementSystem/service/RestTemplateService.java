package com.dinesh.StudentManagementSystem.service;

import com.dinesh.StudentManagementSystem.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
