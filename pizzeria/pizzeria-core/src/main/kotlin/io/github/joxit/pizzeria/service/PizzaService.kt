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
) {
  @Transactional(readOnly = true)
  fun getPizza(name: String): PizzaDTO {
    // Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(pizzaDAO.getPizza(name))
  }

  fun createPizza(pizzaRequest: CreatePizzaRequest): PizzaDTO {
    // Faire une vérification et throw PizzaExistsExceptions quand une pizza existe déjà
    val pizza = Pizza(
      pizzaRequest.name,
      pizzaRequest.price,
      // 1- Faire une vérification et throw IngredientNotFoundExceptions quand un ingrédient n'existe pas
      // 2- Factoriser cette boucle et vérification
      pizzaRequest.ingredients.map { ingredientDAO.findById(it).get() }
    )
    pizzaDAO.createPizza(pizza)
    return PizzaMapper.modelToDTO(pizza)
  }

  fun updatePizza(name: String, pizzaRequest: UpdatePizzaRequest): PizzaDTO {
    // Faire une vérification et throw PizzaNotFoundExceptions quand une pizza n'existe pas
    val pizza = Pizza(name, pizzaRequest.price)
    // Faire une vérification et throw IngredientNotFoundExceptions quand un ingrédient n'existe pas
    // 2- Factoriser cette boucle et vérification
    pizza.ingredients = pizzaRequest.ingredients.map { ingredientDAO.findById(it).get() }
    pizzaDAO.updatePizza(pizza)
    return PizzaMapper.modelToDTO(pizza)
  }

  fun patchPizza(name: String, pizzaRequest: PatchPizzaRequest): PizzaDTO {
    // Faire une vérification et throw PizzaNotFoundExceptions quand une pizza n'existe pas
    val pizza = pizzaDAO.getPizza(name)
    // Faire une vérification et throw IngredientNotFoundExceptions quand un ingrédient n'existe pas
    // 2- Factoriser cette boucle et vérification
    val ingredients = pizzaRequest.ingredients?.map { ingredientDAO.findById(it).get() }
    val newPizza = Pizza(
      name = name,
      price = pizzaRequest.price ?: pizza.price,
      ingredients = ingredients ?: pizza.ingredients
    )
    pizzaDAO.updatePizza(newPizza)
    return PizzaMapper.modelToDTO(newPizza)
  }

  fun deletePizza(name: String) {
    // En général lors d'un delete, quand un élément n'existe pas ce n'est pas grave
    // 1- Ne pas renvoyer une 500 quand la pizza n'existe pas
    // 2- Empêcher la suppression des pizzas par défauts
    pizzaDAO.deletePizza(pizzaDAO.getPizza(name))
  }
}
