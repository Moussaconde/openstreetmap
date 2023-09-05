package io.github.joxit.pizzeria.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table
class IngredientStock(id: Int, stockable: Ingredient, quantity: Int) : Stock<Ingredient>(id, stockable, quantity)
