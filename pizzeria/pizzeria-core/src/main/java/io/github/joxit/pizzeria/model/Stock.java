package io.github.joxit.pizzeria.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Jones Magloire @Joxit
 * @since 2017-11-01
 */
@MappedSuperclass
public abstract class Stock<T extends Stockable> implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected int id;
  //    @ManyToOne
  protected T stockable;
  protected int quantity;
}
