package com.blau.rest;

import com.blau.dto.LogDto;
import com.blau.entity.Log;
import com.blau.service.impl.LogServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogServiceImpl logService;
    private final ModelMapper modelMapper;

    public LogController(LogServiceImpl logService, ModelMapper modelMapper) {
        this.logService = logService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<String> createDrinkLog(@RequestBody LogDto logDto) {
        Log log = modelMapper.map(logDto, Log.class);
        logService.save(log);
        return ResponseEntity.status(201).body("Log created successfully.");
    }
}
