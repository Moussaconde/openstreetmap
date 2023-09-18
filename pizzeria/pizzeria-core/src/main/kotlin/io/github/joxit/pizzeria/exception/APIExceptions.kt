package io.github.joxit.pizzeria.exception

class PizzaExistsExceptions(name: String) : IllegalStateException("The pizza $name already exist")
class PizzaNotFoundExceptions(name: String) : IllegalArgumentException("The pizza $name does not exist")
class IngredientNotFoundExceptions(name: String) : IllegalArgumentException("The Ingredient $name does not exist")
class NotAllowedException(msg: String) : IllegalStateException(msg)