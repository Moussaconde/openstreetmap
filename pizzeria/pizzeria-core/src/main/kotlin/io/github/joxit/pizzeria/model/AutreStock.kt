package io.github.joxit.pizzeria.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table
class AutreStock(id: Int, stockable: ArticleAutre, quantity: Int) : Stock<ArticleAutre>(id, stockable, quantity)
