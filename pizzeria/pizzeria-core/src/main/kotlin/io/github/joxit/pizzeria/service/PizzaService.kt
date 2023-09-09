package io.github.joxit.pizzeria.service

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.exception.HandledException
import io.github.joxit.pizzeria.mapper.PizzaMapper
import io.github.joxit.pizzeria.model.Pizza
import io.github.joxit.pizzeria.persistence.IngredientDAO
import io.github.joxit.pizzeria.persistence.PizzaDAO
import io.github.joxit.pizzeria.persistence.PizzaSDJDAO
import io.github.joxit.pizzeria.request.CreatePizzaRequest
import io.github.joxit.pizzeria.request.PatchPizzaRequest
import io.github.joxit.pizzeria.request.UpdatePizzaRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
// For lazy loading and persist data
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
class PizzaService(
  @Autowired private val pizzaDAO: PizzaDAO,
  @Autowired private val ingredientDAO: IngredientDAO,
  @Autowired private val pizzaMapper: PizzaMapper,
) {
  @Transactional(readOnly = true)
  fun getPizza(name: String): PizzaDTO {
    return pizzaMapper.modelToDTO(pizzaDAO.getPizza(name))
  }

  fun createPizza(pizzaRequest: CreatePizzaRequest): PizzaDTO {
    val pizza = Pizza(
      pizzaRequest.name,
      pizzaRequest.price,
      pizzaRequest.ingredients.map { ingredientDAO.findById(it).get() }
    )
    pizzaDAO.createPizza(pizza)
    return pizzaMapper.modelToDTO(pizza)
  }

  fun updatePizza(name: String, pizzaRequest: UpdatePizzaRequest): PizzaDTO {
    val pizza = Pizza(name, pizzaRequest.price)
    pizza.ingredients = pizzaRequest.ingredients.map { ingredientDAO.findById(it).get() }
    pizzaDAO.updatePizza(pizza)
    return pizzaMapper.modelToDTO(pizza)
  }

  fun patchPizza(name: String, pizzaRequest: PatchPizzaRequest): PizzaDTO {
    val pizza = pizzaDAO.getPizza(name)
    val ingredients = pizzaRequest.ingredients?.map { ingredientDAO.findById(it).get() }
    val newPizza = Pizza(
      name = name,
      price = pizzaRequest.price ?: pizza.price,
      ingredients = ingredients ?: pizza.ingredients
    )
    pizzaDAO.updatePizza(newPizza)
    return pizzaMapper.modelToDTO(newPizza)
  }

  fun deletePizza(name: String) {
    pizzaDAO.deletePizza(name)
  }
}
