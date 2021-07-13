package com.example.clothes.model

import com.google.gson.annotations.SerializedName

class Product (
    @SerializedName("image")
    var image: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("regular_price")
    var regularPrice: String,
    @SerializedName("actual_price")
    var actualPrice: String,
    @SerializedName("on_sale")
    var onSale: Boolean,
    @SerializedName("installments")
    var installments: String,
    @SerializedName("discount_percentage")
    var discountPercentage: String,
    @SerializedName("sizes")
    var sizes: Array<ProductSize>
)