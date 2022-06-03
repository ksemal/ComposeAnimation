package com.example.memorygame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memorygame.R
import com.example.memorygame.ui.theme.MemoryGameTheme

@Composable
fun Card(image: Painter) {
    Box(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colors.primary),
    ) {
        Image(painter = image, contentDescription = null, modifier = Modifier.fillMaxSize().padding(4.dp), colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary))
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MemoryGameTheme {
        Card(painterResource(R.drawable.ic_crocodile))
    }
}