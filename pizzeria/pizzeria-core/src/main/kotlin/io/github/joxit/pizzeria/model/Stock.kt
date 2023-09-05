package io.github.joxit.pizzeria.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.io.Serializable

@MappedSuperclass
abstract class Stock<T : Stockable>(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected val id: Int,
  // @ManyToOne
  protected val stockable: T,
  protected val quantity: Int
) : Serializable