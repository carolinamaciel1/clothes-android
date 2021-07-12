package com.example.clothes.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clothes.model.Product
import com.example.clothes.model.ProductList
import com.example.clothes.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListViewModel : ViewModel() {

    val isLoaded : Boolean
        get() {
            return products.value?.isNotEmpty() == true
        }

     var products: MutableLiveData<List<Product>> = MutableLiveData()

     fun getProducts() {
        val call = RetrofitInitializer().productService().list()
        call.enqueue(object : Callback<ProductList> {

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                response.body()?.let { it ->
                    products.value = it.products ?: listOf()
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                t.message?.let { Log.e("onFailure error", it) }
            }
        })
    }
}
