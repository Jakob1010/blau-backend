package com.blau.service;

import com.blau.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {
    Log save(Log log);
    Log getLogById(Long id);
    Page<Log> finAllLogs(Pageable page);


}
