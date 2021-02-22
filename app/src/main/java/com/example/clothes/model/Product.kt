package com.example.clothes.model

data class Product(
    var image: String,
    var name: String,
    var regular_price: String,
    var actual_price: String,
    var on_sale: Boolean?,
    var installments: String,
    var size: Array<ProductSize>
)