package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ExotelCallService {
    private static final String ACCOUNT_SID = "ofbusiness2";
    private static final String API_KEY = "51e2377a012c5014e4f1985a3855df8131f69c1cb4a65a65";
    private static final String API_TOKEN = "d837adacc4b61c1f8facb48f3a7e0f1f751a5fd565fe33e0";
    private static final String SUBDOMAIN = "api.exotel.com";

    public void makeCall(String from, String to, String callerId) {
        String url = "https://api.exotel.com/v1/Accounts/"
            + ACCOUNT_SID + "/Calls/connect";

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("From", from);
        body.add("To", to);
        body.add("CallerId", callerId);
        body.add("Record", "true");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = API_KEY + ":" + API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        System.out.println("Response: " + response.getBody());
        //response.getBody().get("caller_sid");
        //return new ResponseEntity<>("Call made", HttpStatus.OK);
    }

    public void fetchCallDetails(String callerId) {
        String url = "https://" + SUBDOMAIN + "/v1/Accounts/"
            + ACCOUNT_SID + "/Calls/" + callerId;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String auth = API_KEY + ":" + API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        System.out.println("Response: " + response.getBody());
        //return new ResponseEntity<>("Call made", HttpStatus.OK);
    }

    public MultiValueMap<String, String> getCallDetails() {
        return null;
    }
}
