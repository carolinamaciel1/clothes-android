package com.example.clothes.retrofit.service

import com.example.clothes.model.ProductList
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("59b6a65a0f0000e90471257d")
    fun list() : Call<ProductList>
}