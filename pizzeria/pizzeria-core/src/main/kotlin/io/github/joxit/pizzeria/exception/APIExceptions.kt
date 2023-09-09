package io.github.joxit.pizzeria.exception

class PizzaExistsExceptions(msg: String): IllegalStateException(msg)
class PizzaNotFoundExceptions(msg: String): IllegalArgumentException(msg)
class IngredientNotFoundExceptions(msg: String): IllegalArgumentException(msg)