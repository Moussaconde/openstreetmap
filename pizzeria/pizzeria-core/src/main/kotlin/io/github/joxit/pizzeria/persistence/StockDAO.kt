package io.github.joxit.pizzeria.persistence

import io.github.joxit.pizzeria.model.Stock
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository // For lazy loading
@Transactional
class StockDAO(
  @PersistenceContext private val em: EntityManager
) {
  fun <T : Stock<*>?> updateStock(stock: T) {
    em.merge(stock)
  }
}
