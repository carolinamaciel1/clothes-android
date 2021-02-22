package com.example.clothes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes.R
import com.example.clothes.model.Product
import com.squareup.picasso.Picasso


class ProductsListAdapter(private val products: List<Product>, private val context: Context) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context)
                .inflate(R.layout.activity_cardview_item_clothes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]
        holder.bindView(item)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(product: Product) {
            val imgProduct = itemView.findViewById<ImageView>(R.id.clothes_img_id)
            val titleProduct = itemView.findViewById<TextView>(R.id.clothes_title_id)

            titleProduct.text = product.name

            if (product.image.isNotEmpty()) {
                Picasso.get().load(product.image).into(imgProduct)
            } else {
                // Missing image adjustment
                Picasso.get().load(R.drawable.ic_baseline_error_24).into(imgProduct);
            }

        }
    }
}