package io.github.joxit.pizzeria.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table
class Ingredient(name: String, price: Float) : Stockable(name, price) {
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
  lateinit var pizzas: MutableCollection<Pizza>
}