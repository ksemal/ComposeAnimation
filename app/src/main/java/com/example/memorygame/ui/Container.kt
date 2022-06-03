package com.example.memorygame.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.memorygame.viewmodel.GameViewModel

@Composable
fun Container(model: GameViewModel = GameViewModel()) {
    Column {
        for (row in 0..3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (item in 0..2) {
                    Card(GetImage(model.imageHash))
                }
            }
        }
    }
}

@Composable
fun GetImage(obj: HashMap<Int, ImageObject>): Painter {
    val random = obj.keys.random()
    obj[random]?.let { imageObj ->
        return if (imageObj.count > 0) {
            imageObj.count--
            painterResource(id = imageObj.drawable)
        } else {
            GetImage(obj = obj)
        }
    }
    throw Error("Can't find the right image")
}

data class ImageObject(@DrawableRes val drawable: Int, var count: Int)