package com.blau.service.impl;

import com.blau.entity.Log;
import com.blau.repository.LogRepository;
import com.blau.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log save(Log log) {
        return this.logRepository.save(log);
    }

    @Override
    public Log getLogById(Long id) {
        return this.logRepository.getById(id);
    }

    @Override
    public Page<Log> finAllLogs(Pageable page) {
        return this.logRepository.findAll(page);
    }
}
