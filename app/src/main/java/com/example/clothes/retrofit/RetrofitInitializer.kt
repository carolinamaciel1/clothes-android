package com.example.clothes.retrofit

import com.example.clothes.retrofit.service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit =
        Retrofit.Builder().baseUrl("https://www.mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()


    fun productService() =  retrofit.create(ProductService::class.java)
}
