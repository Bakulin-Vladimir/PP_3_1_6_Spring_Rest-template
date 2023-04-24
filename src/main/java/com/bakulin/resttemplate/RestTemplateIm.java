package com.bakulin.resttemplate;

import com.bakulin.resttemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestTemplateIm {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private String URL = "http://94.198.50.185:7081/api/users";


    @Autowired
    public RestTemplateIm(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie",
                String.join(";", restTemplate.headForHeaders(URL).get("Set-Cookie")));
    }

    public String getAnswer() {
        return addUser().getBody() + "" + updateUser().getBody() + "" + deleteUser().getBody();
    }

    //    @GetMapping
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        System.out.println(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    //    @PostMapping
    public ResponseEntity<String> addUser() {
        User user = new User(3L, "James", "Brown", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> userResponseEntity = restTemplate.postForEntity(URL, entity, String.class);
        return userResponseEntity;
    }

    //    @PutMapping
    public ResponseEntity<String> updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 5);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class, 3);
        return exchange;
    }

    //    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser() {
        Map<String, Long> uriVariables = new HashMap<>() {{
            put("id", 3L);
        }};
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(URL + "/{id}", HttpMethod.DELETE, entity, String.class, uriVariables);
    }
}
