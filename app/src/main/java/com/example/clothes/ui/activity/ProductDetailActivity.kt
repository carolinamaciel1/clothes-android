package com.example.clothes.ui.activity

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
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
        val sizeP = findViewById<TextView>(R.id.size_p)
        val sizePP = findViewById<TextView>(R.id.size_pp)
        val sizeM = findViewById<TextView>(R.id.size_m)
        val sizeG = findViewById<TextView>(R.id.size_g)
        val sizeGG = findViewById<TextView>(R.id.size_gg)


        fun configUIforOnSale(on_sale: Boolean, actual_price: String, discount_percentage: String) {
            onSaleProduct.isVisible = on_sale
            actualPriceProduct.isVisible = on_sale
            priceProduct.paintFlags = if (on_sale) Paint.STRIKE_THRU_TEXT_FLAG else 0
            val discountString = "$actual_price ($discount_percentage off)"
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

        fun setVisibleSizeItem(item: TextView, isVisible: Boolean) {
            (item.layoutParams as LinearLayout.LayoutParams).weight = if (isVisible) 1.0f else 0.0f
            item.width = if (isVisible) LinearLayout.LayoutParams.WRAP_CONTENT else 0
        }

        fun configUIforSizes(sizes_items: Array<ProductSize>) {
            for (size in sizes_items) {
                when (size.size) {
                    "P" -> setVisibleSizeItem(sizeP, size.available)
                    "PP" -> setVisibleSizeItem(sizePP, size.available)
                    "M" -> setVisibleSizeItem(sizeM, size.available)
                    "G" -> setVisibleSizeItem(sizeG, size.available)
                    "GG" -> setVisibleSizeItem(sizeGG, size.available)
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
