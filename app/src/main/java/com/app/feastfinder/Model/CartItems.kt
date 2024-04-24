package com.app.feastfinder.Model

data class CartItems(
    var foodName: String ?= null,
    var foodPrice: String ?= null,
    var foodDescription: String ?= null,
    var foodImg: String ?= null,
    var foodQuantity: Int ?= null
)
