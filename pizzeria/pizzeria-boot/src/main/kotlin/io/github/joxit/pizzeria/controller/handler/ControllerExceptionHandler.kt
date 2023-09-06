package io.github.joxit.pizzeria.controller.handler

import io.github.joxit.pizzeria.dto.ErrorDTO
import io.github.joxit.pizzeria.exception.HandledException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)
  }

  @ExceptionHandler(HandledException::class)
  fun handledException(e: HandledException): ResponseEntity<ErrorDTO> {
    LOGGER.warn(e.message)
    return ResponseEntity(ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.message.orEmpty()), HttpStatus.BAD_REQUEST)
  }
}