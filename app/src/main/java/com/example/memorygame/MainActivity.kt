package com.example.memorygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memorygame.ui.Container
import com.example.memorygame.ui.theme.MemoryGameTheme
import com.example.memorygame.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model: GameViewModel = GameViewModel()
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
                                    Text(
                                        "Memory Game",
                                        fontSize = 25.sp,
                                        color = MaterialTheme.colors.secondary
                                    )
                                    ScoreText(vm = model)

                                }

                            },
                            backgroundColor = MaterialTheme.colors.background,
                            elevation = 8.dp
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Container(model)
                }
            }
        }
    }
}

@Composable
fun ScoreText(vm: GameViewModel) {
    val score = vm.score.observeAsState().value
    Text(
        text = "Score: ${score}",
        fontSize = 20.sp,
        color = MaterialTheme.colors.onPrimary,
    )
}