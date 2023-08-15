package com.blau.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LogEndpoint.BASE_URL)
@Slf4j
public class DrinkEndpoint {
    public static final String BASE_URL =  "/api/v1/drinks";

    
}
