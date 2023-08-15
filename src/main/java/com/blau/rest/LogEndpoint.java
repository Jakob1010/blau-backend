package com.blau.rest;

import com.blau.dto.LogDto;
import com.blau.mapper.LogMapper;
import com.blau.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LogEndpoint.BASE_URL)
@Slf4j
public class LogEndpoint {
    public static final String BASE_URL =  "/api/v1/logs";
    private final LogServiceImpl logService;
    private final LogMapper logMapper;

    public LogEndpoint(LogServiceImpl logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @PostMapping
    public ResponseEntity<String> createLog(@RequestBody LogDto logDto) {
        log.info("POST {}", BASE_URL);
        logService.save(logMapper.logDtoToLogEntity(logDto));
        return ResponseEntity.status(201).body("Log created successfully.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LogDto getLog(@PathVariable Long id) {
        log.info("GET {}/{}", BASE_URL, id);
        return logMapper.logEntityToLogDto(logService.getLogById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<LogDto> getAllLogs(Pageable pageable) {
        log.info("GET {}", BASE_URL);
        return logMapper.toDtoPage(logService.finAllLogs(pageable));
    }

}
