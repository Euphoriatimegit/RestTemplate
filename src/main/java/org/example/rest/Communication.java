package org.example.rest;

import org.example.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final String URL = "http://91.241.64.178:7081/api/users";

    @Autowired
    private RestTemplate restTemplate;

    public String key() {
        String result ;
        List<String> session = restTemplate.exchange(URL, HttpMethod.GET, null, String.class).getHeaders().get("Set-Cookie");
        String sessionID = "";
        for (String res : session) {
            sessionID += res;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",sessionID);
        User user = new User(3l,"James","Brown",(byte)25);
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        result = restTemplate.exchange(URL,HttpMethod.POST,entity,String.class).getBody();
        User updateUser = new User(3l,"Thomas","Shelby",(byte)25);
        HttpEntity<User> entity1 = new HttpEntity<>(updateUser,headers);
        result += restTemplate.exchange(URL,HttpMethod.PUT,entity1,String.class).getBody();
        HttpEntity<User> entity2 = new HttpEntity<>(headers);
        result += restTemplate.exchange(URL+"/"+3,HttpMethod.DELETE,entity1,String.class).getBody();


        return result;

    }

//    public User getUserById(Long id){
//        User user = restTemplate.getForObject(URL + "/" + id,User.class);
//        return user;
//    }

//    public String saveUsers(User user) {
//
//        return responseEntity.getBody();
//    }


    public String updateUsers(User user,List<String> session) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",session.stream().collect(Collectors.joining(";")));
        System.out.println(headers.get("Set-Cookie"));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return "responseEntity.getBody()";
    }

    public void deleteUsers(Long id) {
        restTemplate.delete(URL + "/" + id);
    }
}
