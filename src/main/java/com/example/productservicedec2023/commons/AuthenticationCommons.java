package com.example.productservicedec2023.commons;

import com.example.productservicedec2023.dtos.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }



    public boolean validateToken(String authToken){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.exchange(
                "http://localhost:8181/users/validate/" + authToken,
                HttpMethod.POST,
                entity,
                UserDto.class);

        if(userDtoResponseEntity.getBody() == null) {
            return false;
        }
        return true;
    }
}
