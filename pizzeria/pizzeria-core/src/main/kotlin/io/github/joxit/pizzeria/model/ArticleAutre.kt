package io.github.joxit.pizzeria.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table
class ArticleAutre(name: String, price: Float) : Stockable(name, price)
