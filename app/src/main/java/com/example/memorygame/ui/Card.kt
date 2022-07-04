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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.memorygame.viewmodel.GameViewModel

enum class FlipCard(val angle: Float) {
    Forward(0f) {
        override val next: FlipCard get() = Previous
    },
    Previous(180f) {
        override val next: FlipCard get() = Forward
    };

    abstract val next: FlipCard
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardImg(
    id: Int,
    image: Painter,
    model: GameViewModel
) {

    var flipCard by remember { mutableStateOf(FlipCard.Forward) }
    val ids = model.openedCards.observeAsState().value

    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 50f else 1f)

    if (model.currentlyOpenedCards.size == 0) {
        flipCard = FlipCard.Forward
        selected.value = false
        if (ids?.indexOf(id) != -1) {
            flipCard = FlipCard.Previous
        }

    }


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
            .background(if (selected.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant)
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12f * density
            }
    ) {
        if (rotation.value <= 90f) {
            Box(
                modifier = Modifier
                    .scale(scale.value)
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(MaterialTheme.colors.secondary)
                    .clickable(
                        onClick = {
                            selected.value = true
                            flipCard = FlipCard.Previous
                            model.addToOpenedCard(id)
                        }
                    )
            )
        } else {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (!selected.value) 20.dp else 6.dp),
                colorFilter = ColorFilter.tint(if (selected.value) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant)
            )
        }
    }
}

