package com.example.clothes.model

import com.google.gson.annotations.SerializedName

class ProductList {
    @SerializedName("products")
    val products: List<Product>? = null
}