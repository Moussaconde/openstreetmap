package io.github.joxit.pizzeria.controller

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.service.PizzeriaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/pizzas")
class PizzaController(
  @Autowired private val pizzeriaService: PizzeriaService
) {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(PizzaController::class.java)
  }

  @GetMapping
  fun getPizzeria(@RequestParam(value = "type", required = false) type: String?): List<PizzaDTO> {
    LOGGER.info("getPizzeria({})", type)
    return pizzeriaService.getAll(type)
  }
}