package com.example.clothes.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes.R
import com.example.clothes.databinding.ActivityCardviewItemClothesBinding
import com.example.clothes.model.Product
import com.example.clothes.ui.activity.ProductDetailActivity
import com.example.clothes.utils.putExtraJson
import com.squareup.picasso.Picasso


class ProductsListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingCardView = ActivityCardviewItemClothesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(bindingCardView)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]
        holder.bindView(item)
    }


    class ViewHolder(binding: ActivityCardviewItemClothesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val imgProduct = binding.imgProduct
        private val titleProduct = binding.clothesTitle
        private val tagOffer = binding.tagOffer

        var product: Product? = null

        init {
            itemView.setOnClickListener {
                product?.let {
                    val intent = Intent(itemView.context, ProductDetailActivity::class.java)
                    intent.putExtraJson(it)
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bindView(product: Product) {
            this.product = product
            titleProduct.text = product.name

            if (product.on_sale) {
                tagOffer.visibility = View.VISIBLE
            }else{
                tagOffer.visibility = View.INVISIBLE
            }

            if (product.image.isNotEmpty()) {
                Picasso.get().load(product.image).into(imgProduct)
            } else {
                Picasso.get().load(R.color.gray).into(imgProduct);
            }
        }
    }
}