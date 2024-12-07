package com.example.blau.exception

import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @ExceptionHandler(Exception::class)
    fun handleAllOtherExceptions(ex: Exception) {
        log.error("An unexpected error occurred ${ex.message}")
    }
}
