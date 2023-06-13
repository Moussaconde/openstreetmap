package io.github.joxit.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Collection;

/**
 * @author Jones Magloire @Joxit
 * @since 2017-11-01
 */
@Entity
@Table
public class Ingredient extends Stockable {

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
  private Collection<Pizza> pizzas;
}
