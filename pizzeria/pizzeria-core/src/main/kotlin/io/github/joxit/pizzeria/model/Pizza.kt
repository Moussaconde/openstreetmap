package io.github.joxit.pizzeria.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table
class Pizza(name: String, price: Float) : Stockable(name, price) {

  constructor(name: String, price: Float, ingredients: Collection<Ingredient>): this(name, price) {
    this.ingredients = ingredients
  }

  @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
  @JoinTable(
    name = "MtM_Pizza_Ingredient",
    joinColumns = [JoinColumn(name = "pizza", referencedColumnName = "name")],
    inverseJoinColumns = [JoinColumn(name = "ingredient", referencedColumnName = "name")]
  )
  lateinit var ingredients: Collection<Ingredient>
}