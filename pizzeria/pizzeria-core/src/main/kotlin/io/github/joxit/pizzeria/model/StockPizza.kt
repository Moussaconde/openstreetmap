package io.github.joxit.pizzeria.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table
class StockPizza(id: Int, stockable: Pizza, quantity: Int) : Stock<Pizza>(id, stockable, quantity)
