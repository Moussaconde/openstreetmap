package io.github.joxit.pizzeria.request

import kotlinx.serialization.Serializable

@Serializable
data class CreatePizzaRequest(val name: String, val price: Float, val ingredients: List<String>)
@Serializable
data class UpdatePizzaRequest(val price: Float, val ingredients: List<String>)
@Serializable
data class PatchPizzaRequest(val price: Float?, val ingredients: List<String>?)