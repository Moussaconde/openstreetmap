package io.github.joxit.pizzeria.service

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.exception.IngredientNotFoundExceptions
import io.github.joxit.pizzeria.exception.NotAllowedException
import io.github.joxit.pizzeria.exception.PizzaExistsExceptions
import io.github.joxit.pizzeria.exception.PizzaNotFoundExceptions
import io.github.joxit.pizzeria.mapper.PizzaMapper
import io.github.joxit.pizzeria.model.Pizza
import io.github.joxit.pizzeria.persistence.IngredientDAO
import io.github.joxit.pizzeria.persistence.PizzaDAO
import io.github.joxit.pizzeria.request.CreatePizzaRequest
import io.github.joxit.pizzeria.request.PatchPizzaRequest
import io.github.joxit.pizzeria.request.UpdatePizzaRequest
import kotlin.jvm.optionals.getOrNull
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
    val pizza = pizzaDAO.getPizza(name) ?: throw PizzaNotFoundExceptions("The pizza $name does not exist")
    // Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(pizza)
  }

  fun createPizza(pizzaRequest: CreatePizzaRequest): PizzaDTO {
    if (pizzaDAO.getPizza(pizzaRequest.name) != null) throw PizzaExistsExceptions("The pizza ${pizzaRequest.name} already exist")

    val pizza = Pizza(pizzaRequest.name, pizzaRequest.price,
      // TODO Factoriser cette boucle
      pizzaRequest.ingredients.map {
        ingredientDAO.findById(it).getOrNull()
          ?: throw IngredientNotFoundExceptions("The Ingredient $it does not exist")
      }
    )
    pizzaDAO.createPizza(pizza)
    return PizzaMapper.modelToDTO(pizza)
  }

  fun updatePizza(name: String, pizzaRequest: UpdatePizzaRequest): PizzaDTO {
    val pizza = pizzaDAO.getPizza(name) ?: throw PizzaNotFoundExceptions("The pizza $name does not exist")
    if (pizza.protected) throw NotAllowedException("The pizza `${pizza.name}` is protected")

    val updatedPizza = Pizza(name, pizzaRequest.price)
    // TODO Factoriser cette boucle
    updatedPizza.ingredients = pizzaRequest.ingredients.map {
      ingredientDAO.findById(it).getOrNull() ?: throw IngredientNotFoundExceptions("The Ingredient $it does not exist")
    }
    pizzaDAO.updatePizza(updatedPizza)
    return PizzaMapper.modelToDTO(updatedPizza)
  }

  fun patchPizza(name: String, pizzaRequest: PatchPizzaRequest): PizzaDTO {
    val pizza = pizzaDAO.getPizza(name) ?: throw PizzaNotFoundExceptions("The pizza $name does not exist")

    // TODO Factoriser cette boucle
    val ingredients = pizzaRequest.ingredients?.map {
      ingredientDAO.findById(it).getOrNull() ?: throw IngredientNotFoundExceptions("The Ingredient $it does not exist")
    }
    val newPizza = Pizza(
      name = name, price = pizzaRequest.price ?: pizza.price, ingredients = ingredients ?: pizza.ingredients
    )
    pizzaDAO.updatePizza(newPizza)
    return PizzaMapper.modelToDTO(newPizza)
  }

  fun deletePizza(name: String) {
    // En général lors d'un delete, quand un élément n'existe pas ce n'est pas grave
    val pizza = pizzaDAO.getPizza(name) ?: return
    if (pizza.protected) throw NotAllowedException("The pizza `${pizza.name}` is protected")
    pizzaDAO.deletePizza(pizza)
  }
}
