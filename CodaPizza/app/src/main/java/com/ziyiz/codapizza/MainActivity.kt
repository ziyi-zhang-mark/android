package com.ziyiz.codapizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.ziyiz.codapizza.model.Topping
import com.ziyiz.codapizza.model.ToppingPlacement
import com.ziyiz.codapizza.ui.PizzaBuilderScreen
import com.ziyiz.codapizza.ui.ToppingCell

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PizzaBuilderScreen()
        }
    }
}