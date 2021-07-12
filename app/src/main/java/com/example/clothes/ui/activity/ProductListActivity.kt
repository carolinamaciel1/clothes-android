package com.example.clothes.ui.activity

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes.databinding.ActivityProductListBinding
import com.example.clothes.model.Product
import com.example.clothes.ui.adapter.ProductsListAdapter
import com.example.clothes.viewModel.ProductListViewModel

class ProductListActivity(
    var viewModel: ProductListViewModel = ProductListViewModel()
) : BaseAppActivity() {
    private lateinit var productListActivityBinding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        productListActivityBinding = ActivityProductListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(productListActivityBinding.root)

        addObserverForDataLoaded()
        observerProperties()
        getData()
    }

    private fun getData() {
        viewModel.getProducts()
    }

    private fun observerProperties() {
        viewModel.products.observe(this, {
            configureList(it)
        })
    }

    private fun addObserverForDataLoaded() {
        val content: RecyclerView = productListActivityBinding.clothesRecyclerview
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.isLoaded) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun configureList(products: List<Product>) {
        productListActivityBinding.clothesRecyclerview.let { recyclerView ->
            recyclerView.adapter = ProductsListAdapter(products)
            val layoutManager = GridLayoutManager(this, 2)
            recyclerView.layoutManager = layoutManager
        }
    }
}

