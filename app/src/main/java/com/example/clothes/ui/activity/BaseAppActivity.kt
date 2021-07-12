package com.example.clothes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.clothes.R

open class BaseAppActivity : AppCompatActivity() {

    override fun setTheme(resId: Int) {
        super.setTheme(R.style.Theme_Clothes)
    }

}