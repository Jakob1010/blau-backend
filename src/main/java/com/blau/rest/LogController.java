package com.blau.rest;

import com.blau.dto.LogDto;
import com.blau.entity.Log;
import com.blau.mapper.LogMapper;
import com.blau.service.impl.LogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogServiceImpl logService;
    private final LogMapper logMapper;

    public LogController(LogServiceImpl logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @PostMapping
    public ResponseEntity<String> createDrinkLog(@RequestBody LogDto logDto) {
        Log log = logMapper.logDtoToLogEntity(logDto);
        logService.save(log);
        return ResponseEntity.status(201).body("Log created successfully.");
    }
}
