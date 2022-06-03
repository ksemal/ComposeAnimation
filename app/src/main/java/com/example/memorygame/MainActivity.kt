package com.example.memorygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.memorygame.ui.Container
import com.example.memorygame.ui.theme.MemoryGameTheme
import com.example.memorygame.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoryGameTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("Memory Game",fontSize = 25.sp, color = MaterialTheme.colors.secondary)
                                    Text(
                                        text = "Score: 0",
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colors.onPrimary,
                                    )

                                }

                            },
                            backgroundColor = MaterialTheme.colors.background,
                            elevation = 8.dp
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Container()
                }
            }
        }
    }
}