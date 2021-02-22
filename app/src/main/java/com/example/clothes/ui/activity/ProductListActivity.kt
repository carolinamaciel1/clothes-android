package com.example.clothes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes.model.Product
import com.example.clothes.model.ProductList
import com.example.clothes.R
import com.example.clothes.retrofit.RetrofitInitializer
import com.example.clothes.ui.adapter.ProductsListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)


        val call = RetrofitInitializer().productService().list()
        call.enqueue(object : Callback<ProductList> {

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                response.body()?.let {
                    val products: List<Product>? = it.products
                    if (products != null) {
                        configureList(products)
                    }
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                t.message?.let { Log.e("onFailure error", it) }
            }
        })
    }

    fun configureList(products: List<Product>) {
        val recycleView = findViewById<RecyclerView>(R.id.clothes_recyclerview)
        recycleView.adapter = ProductsListAdapter(products, this)
        val layoutManager = GridLayoutManager(this, 2)
        recycleView.layoutManager = layoutManager
    }
}

