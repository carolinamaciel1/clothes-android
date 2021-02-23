package com.example.clothes.ui.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.clothes.R
import com.example.clothes.model.Product
import com.example.clothes.model.ProductSize
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
        val linearLayoutSize = findViewById<LinearLayout>(R.id.size_linear_layout)


        fun configUIforOnSale(on_sale: Boolean, actual_price: String, discount_percentage: String) {
            onSaleProduct.isVisible = on_sale
            actualPriceProduct.isVisible = on_sale
            priceProduct.paintFlags = if (on_sale) Paint.STRIKE_THRU_TEXT_FLAG else 0
            val discountString = "$actual_price ($discount_percentage OFF)"
            val discountSpannable = SpannableString(discountString)
            val startIndex = discountString.indexOf("(", 0)
            discountSpannable.setSpan(
                ForegroundColorSpan(Color.RED),
                startIndex,
                discountString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            discountSpannable.setSpan(
                RelativeSizeSpan(0.7f),
                startIndex,
                discountString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            actualPriceProduct.text = discountSpannable
        }


        fun configUIforSizes(sizes_items: Array<ProductSize>) {
            for (size in sizes_items) {
                if (size.available) {
                    val textView = TextView(this)
                    val textViewContent = size.size
                    val layout = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    layout.setMargins(0,0,25,50)
                    textView.layoutParams = layout
                    textView.setPadding(20,20,20,20)
                    textView.setBackgroundResource(R.drawable.size_style)
                    textView.textSize = 15f
                    textView.gravity = Gravity.CENTER
                    textView.text = textViewContent
                    linearLayoutSize?.addView(textView)

                }
            }
        }

        product?.let {
            nameProduct.text = it.name
            priceProduct.text = it.regular_price
            installmentsProduct.text = it.installments
            configUIforSizes(it.sizes)
            if (!it.image.isEmpty()) {
                Picasso.get().load(product.image).into(imageProduct)
            } else {
                Picasso.get().load(R.mipmap.ic_launcher).into(imageProduct);
            }
            configUIforOnSale(it.on_sale, it.actual_price, it.discount_percentage)
        }
    }
}
