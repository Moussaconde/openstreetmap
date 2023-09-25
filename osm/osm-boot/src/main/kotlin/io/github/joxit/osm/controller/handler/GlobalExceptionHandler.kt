package io.github.joxit.osm.controller.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice

/**
 * C'est ici que vous devez gérer les exceptions qui sont levés. Utilisation de ControllerAdvice et ExceptionHandler.
 */
@ControllerAdvice
class GlobalExceptionHandler {
  fun illegalArgumentException(ex: IllegalArgumentException?): ResponseEntity<Any> {
    val errorMessage = "Erreur de validation : ${ex?.message}"
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage)

  }
}
