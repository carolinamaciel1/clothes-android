package com.example.clothes.viewModel

import android.content.Intent
import com.example.clothes.model.Product
import com.example.clothes.utils.getJsonExtra

class ProductDetailViewModel(val intent: Intent){
    val product: Product?
        get() = intent.getJsonExtra(Product::class.java)
}