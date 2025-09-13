package com.example.demo.controller;

import com.example.demo.service.ExotelCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/v1/call")
public class ExotelCallController {

    @Autowired
    private ExotelCallService exotelCallService;

    @PostMapping("/new")
    public ResponseEntity<String> makeCall(
        @RequestParam String callerMobileNumber,
        @RequestParam String customerMobileNumber,
        @RequestParam String callerId) {
        exotelCallService.makeCall(callerMobileNumber, customerMobileNumber, callerId);
        return new ResponseEntity<>("Call request sent", HttpStatus.OK);
    }

    @GetMapping("/fetch/{callerId}")
    public ResponseEntity fetchCallDetails(@PathVariable String callerId) {
        exotelCallService.fetchCallDetails(callerId);
        return new ResponseEntity<>("Call details fetched", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity getCallDetails(){
        return new ResponseEntity<>(exotelCallService.getCallDetails(), HttpStatus.OK);
    }

}
