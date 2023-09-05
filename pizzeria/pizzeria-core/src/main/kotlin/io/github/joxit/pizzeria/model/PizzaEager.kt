package io.github.joxit.pizzeria.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "Pizza")
class PizzaEager(
  name: String,
  price: Float,
  @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
  @JoinTable(
    name = "MtM_Pizza_Ingredient",
    joinColumns = [JoinColumn(name = "pizza", referencedColumnName = "name")],
    inverseJoinColumns = [JoinColumn(name = "ingredient", referencedColumnName = "name")]
  )
  val ingredients: Collection<Ingredient>
) : Stockable(name, price) {

  override fun toString(): String {
    return "PizzaEager{" +
        "ingredients=" + ingredients + ", " + super.toString() +
        '}'
  }
}