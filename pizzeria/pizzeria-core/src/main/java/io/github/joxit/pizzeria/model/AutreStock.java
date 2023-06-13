package io.github.joxit.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Jones Magloire @Joxit
 * @since 2017-11-01
 */
@Entity
@Table
public class AutreStock extends Stock<ArticleAutre> {
}
