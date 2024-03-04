package com.example.YouTube.service;

import com.example.YouTube.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class HttpTestService {
    @Autowired
    private RestTemplate restTemplate;
    public void createTask() {
        RestTemplate restTemplate = new RestTemplate();

        TaskDTO dto = new TaskDTO();
        dto.setTitle("Test title 1");
        dto.setContent("Salom...");

        HttpEntity<TaskDTO> request = new HttpEntity<TaskDTO>(dto);

        String response = restTemplate.postForObject("http://localhost:8081/task", request, String.class);
        System.out.println(response);
    }

    public void taskGetAll() {
        RestTemplate restTemplate = new RestTemplate();
        List result = restTemplate.getForObject("http://localhost:8081/task/getAll", List.class);
        System.out.println(result);
    }
    public void getTaskById(String id) {
        String url = "http://localhost:8081/task/" + id;
        ResponseEntity<TaskDTO> responseEntity = restTemplate.getForEntity(url, TaskDTO.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(responseEntity.getBody());
        }
    }


}
