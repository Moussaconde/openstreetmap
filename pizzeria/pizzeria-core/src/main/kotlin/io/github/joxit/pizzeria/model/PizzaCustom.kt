package io.github.joxit.pizzeria.model

class PizzaCustom(
  name: String,
  price: Float,
  val ingredients: MutableCollection<Ingredient>
) : Stockable(name, price) {

  override fun toString(): String {
    return "PizzaCustom{" +
        "ingredients=" + ingredients + ", " + super.toString() +
        '}'
  }
}