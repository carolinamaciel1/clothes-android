package com.example.clothes.ui.activity

import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.clothes.R
import com.example.clothes.model.Product
import com.example.clothes.utils.getJsonExtra
import com.squareup.picasso.Picasso


class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product: Product? = intent.getJsonExtra(Product::class.java)
        val imageProduct = findViewById<ImageView>(R.id.img_product)
        val nameProduct = findViewById<TextView>(R.id.name_product)
        val priceProduct = findViewById<TextView>(R.id.price_product)
        val installmentsProduct = findViewById<TextView>(R.id.installments_product)
        val onSaleProduct = findViewById<ImageView>(R.id.on_sale_product)
        val actualPriceProduct = findViewById<TextView>(R.id.actual_price_product)

        fun configUIforOnSale(on_sale: Boolean, actual_price: String){
            onSaleProduct.isVisible = on_sale
            actualPriceProduct.isVisible = on_sale
            priceProduct.paintFlags = if (on_sale) Paint.STRIKE_THRU_TEXT_FLAG else 0
            actualPriceProduct.text = actual_price
        }

        product?.let {
            nameProduct.text = it.name
            priceProduct.text = it.regular_price
            installmentsProduct.text = it.installments
            if (!it.image.isEmpty()) {
                Picasso.get().load(product.image).into(imageProduct)
            }else{
                Picasso.get().load(R.mipmap.ic_launcher).into(imageProduct);
            }

            configUIforOnSale(it.on_sale, it.actual_price)

        }



    }
}
