package com.yotfr.owes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yotfr.owes.app.screens.NavBarScreen
import com.yotfr.owes.app.theme.OwesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OwesTheme {
                // A surface container using the 'background' color from the theme
                NavBarScreen()
            }
        }
    }
}
