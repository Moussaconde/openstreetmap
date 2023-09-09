package io.github.joxit.pizzeria.mapper

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.model.Ingredient
import io.github.joxit.pizzeria.model.Pizza
import io.github.joxit.pizzeria.model.PizzaCustom
import io.github.joxit.pizzeria.model.PizzaEager
import org.springframework.stereotype.Component

/**
 * Old Java version : https://github.com/Joxit/IG-Master2/tree/promo-2021/pizzeria/pizzeria-core/src/main/java/io/github/joxit/pizzeria
 * @author Jones Magloire @Joxit
 * @since Java: 2017-11-01
 * @since Kotlin: 2022
 */
object PizzaMapper {
  fun modelToDTO(pizza: Pizza): PizzaDTO {
    val ingredients = mutableListOf<String>()
    // Boucle traditionnelle
    for (ingredient in pizza.ingredients) {
      ingredients.add(ingredient.name)
    }
    return PizzaDTO(
      pizza.name,
      pizza.price,
      ingredients
    )
  }

  fun modelToDTO(pizza: PizzaEager): PizzaDTO {
    return PizzaDTO(
      pizza.name,
      pizza.price,
      // Fonction lambda map avec it qui représente l'objet actuel
      pizza.ingredients.map { it.name }
    )
  }

  fun modelToDTO(pizza: PizzaCustom): PizzaDTO {
    return PizzaDTO(
      pizza.name,
      pizza.price,
      // Fonction lambda map avec référence sur méthode/champs
      pizza.ingredients.map(Ingredient::name)
    )
  }
}