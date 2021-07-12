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
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.clothes.R
import com.example.clothes.databinding.ActivityProductDetailBinding
import com.example.clothes.model.ProductSize
import com.example.clothes.viewModel.ProductDetailViewModel
import com.squareup.picasso.Picasso


class ProductDetailActivity : BaseAppActivity() {
    private lateinit var productDetailActivityBinding: ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        productDetailActivityBinding = ActivityProductDetailBinding.inflate(layoutInflater)
        viewModel= ProductDetailViewModel(intent)
        super.onCreate(savedInstanceState)
        setContentView(productDetailActivityBinding.root)

        configUI()
    }

    private fun configUI() {
        viewModel.product?.let { product ->
            productDetailActivityBinding.let {
                it.nameProduct.text = product.name
                it.priceProduct.text = product.regular_price
                it.installmentsProduct.text = product.installments

                configUIForSizes(product.sizes)

                if (product.image.isNotEmpty()) {
                    Picasso.get().load(product.image).into(productDetailActivityBinding.imgProduct)
                } else {
                    Picasso.get().load(R.drawable.ic_launcher_foreground)
                        .into(productDetailActivityBinding.imgProduct);
                }

                configUIForOnSale(product.on_sale, product.actual_price, product.discount_percentage)
            }
        }
    }

    private fun configUIForOnSale(
        on_sale: Boolean,
        actual_price: String,
        discount_percentage: String
    ) {
        val productDetail = productDetailActivityBinding
        productDetail.onSaleProduct.isVisible = on_sale
        productDetail.actualPriceProduct.isVisible = on_sale
        productDetail.priceProduct.paintFlags = if (on_sale) Paint.STRIKE_THRU_TEXT_FLAG else 0
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
        productDetail.actualPriceProduct.text = discountSpannable
    }


    private fun configUIForSizes(sizes_items: Array<ProductSize>) {
        for (size in sizes_items) {
            if (size.available) {
                val textView = TextView(this)
                val textViewContent = size.size
                val layout = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layout.setMargins(0, 0, 25, 50)
                textView.layoutParams = layout
                textView.setPadding(50, 50, 50, 50)
                textView.setBackgroundResource(R.drawable.size_style)
                textView.textSize = 15f
                textView.gravity = Gravity.CENTER
                textView.text = textViewContent
                productDetailActivityBinding.sizeLinearLayout.addView(textView)

            }
        }
    }
}
