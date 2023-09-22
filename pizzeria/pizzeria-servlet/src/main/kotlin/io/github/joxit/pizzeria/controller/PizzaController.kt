package io.github.joxit.pizzeria.controller

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.service.PizzeriaService
import jakarta.ws.rs.QueryParam
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping(path = ["/pizzas", "/pizzas/"])
class PizzaController(
  @Autowired private val pizzeriaService: PizzeriaService
) {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(PizzaController::class.java)
  }

  // Equivalent to @Get
  @RequestMapping(method = [RequestMethod.GET])
  fun getPizzeria(@QueryParam("type") type: String?): List<PizzaDTO> {
    LOGGER.info("getPizzeria({})", type)
    return pizzeriaService.getAll(type)
  }
}
