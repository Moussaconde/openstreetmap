package io.github.joxit.osm.controller.handler

import org.springframework.http.ResponseEntity

/**
 * C'est ici que vous devez gérer les exceptions qui sont levés. Utilisation de ControllerAdvice et ExceptionHandler.
 */
class GlobalExceptionHandler {
  fun illegalArgumentException(ex: IllegalArgumentException?): ResponseEntity<Any> {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }
}
