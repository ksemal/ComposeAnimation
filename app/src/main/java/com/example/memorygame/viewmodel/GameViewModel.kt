package com.example.memorygame.viewmodel

import androidx.lifecycle.ViewModel
import com.example.memorygame.R
import com.example.memorygame.ui.ImageObject

class GameViewModel : ViewModel() {
    val imageHash: HashMap<Int, ImageObject> = hashMapOf(
        0 to ImageObject(R.drawable.ic_bear, 2),
        1 to ImageObject(R.drawable.ic_crocodile, 2),
        2 to ImageObject(R.drawable.ic_elephant, 2),
        3 to ImageObject(R.drawable.ic_giraffe, 2),
        4 to ImageObject(R.drawable.ic_rabbit, 2),
        5 to ImageObject(R.drawable.ic_reindeer, 2)
    )
}