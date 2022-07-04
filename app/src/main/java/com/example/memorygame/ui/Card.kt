package com.example.memorygame.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memorygame.R
import com.example.memorygame.ui.theme.MemoryGameTheme

enum class FlipCard(val angle: Float) {
    Forward(0f) {
        override val next: FlipCard get() = Previous
    },
    Previous(180f) {
        override val next: FlipCard get() = Forward
    };

    abstract val next: FlipCard
}

@Composable
fun CardImg(
    image: Painter,
) {
    var flipCard by remember { mutableStateOf(FlipCard.Forward) }
    val rotation = animateFloatAsState(
        targetValue = flipCard.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    )
    Box(
        modifier = Modifier
            .size(100.dp, 100.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(MaterialTheme.colors.primary)
            .clickable(
                onClick = {
                    flipCard = flipCard.next
                    println("flip back")
                }
            )
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12f * density
            }
    ) {
        if (rotation.value <= 90f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(MaterialTheme.colors.secondary)
            )
        } else {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp), colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MemoryGameTheme {
        CardImg(painterResource(R.drawable.ic_crocodile))
    }
}