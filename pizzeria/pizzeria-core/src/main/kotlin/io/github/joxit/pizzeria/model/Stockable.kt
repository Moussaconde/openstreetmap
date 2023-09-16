package io.github.joxit.pizzeria.model

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.io.Serializable

@MappedSuperclass
abstract class Stockable(
  @Id val name: String, val price: Float = 0f, val protected: Boolean = false
) : Serializable {
  override fun hashCode(): Int {
    return name.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    return if (other is PizzaCustom) name == other.name else false
  }
}