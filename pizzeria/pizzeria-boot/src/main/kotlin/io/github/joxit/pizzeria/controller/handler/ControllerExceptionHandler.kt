package io.github.joxit.pizzeria.controller.handler

import io.github.joxit.pizzeria.dto.ErrorDTO
import io.github.joxit.pizzeria.exception.HandledException
import io.github.joxit.pizzeria.exception.IngredientNotFoundExceptions
import io.github.joxit.pizzeria.exception.NotAllowedException
import io.github.joxit.pizzeria.exception.PizzaExistsExceptions
import io.github.joxit.pizzeria.exception.PizzaNotFoundExceptions
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

  @ExceptionHandler(PizzaExistsExceptions::class)
  fun pizzaExistsException(e: PizzaExistsExceptions): ResponseEntity<ErrorDTO> {
    LOGGER.warn(e.message)
    return ResponseEntity(ErrorDTO(HttpStatus.UNAUTHORIZED.value(), e.message.orEmpty()), HttpStatus.UNAUTHORIZED)
  }

  @ExceptionHandler(PizzaNotFoundExceptions::class)
  fun pizzaNotFoundException(e: PizzaNotFoundExceptions): ResponseEntity<ErrorDTO> {
    LOGGER.warn(e.message)
    return ResponseEntity(ErrorDTO(HttpStatus.NOT_FOUND.value(), e.message.orEmpty()), HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(IngredientNotFoundExceptions::class)
  fun ingredientNotFoundException(e: IngredientNotFoundExceptions): ResponseEntity<ErrorDTO> {
    LOGGER.warn(e.message)
    return ResponseEntity(ErrorDTO(HttpStatus.NOT_FOUND.value(), e.message.orEmpty()), HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(NotAllowedException::class)
  fun notAllowedException(e: NotAllowedException): ResponseEntity<ErrorDTO> {
    LOGGER.warn(e.message)
    return ResponseEntity(ErrorDTO(HttpStatus.FORBIDDEN.value(), e.message.orEmpty()), HttpStatus.FORBIDDEN)
  }
}