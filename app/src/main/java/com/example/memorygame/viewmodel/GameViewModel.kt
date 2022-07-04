package com.example.memorygame.viewmodel

import android.os.Handler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memorygame.R
import com.example.memorygame.ui.ImageObject

class GameViewModel : ViewModel() {
    val imageHash: HashMap<Int, ImageObject> = hashMapOf(
        0 to ImageObject(100, R.drawable.ic_bear, 2),
        1 to ImageObject(200, R.drawable.ic_crocodile, 2),
        2 to ImageObject(300, R.drawable.ic_elephant, 2),
        3 to ImageObject(400, R.drawable.ic_giraffe, 2),
        4 to ImageObject(500, R.drawable.ic_rabbit, 2),
        5 to ImageObject(600, R.drawable.ic_reindeer, 2)
    )

    private val _openedCards = MutableLiveData(mutableListOf<Int>())
    val openedCards: LiveData<MutableList<Int>> = _openedCards
    val currentlyOpenedCards = mutableListOf<Int>()


    fun addToOpenedCard(id: Int) {
        currentlyOpenedCards.add(id)

        var counter = 0
        currentlyOpenedCards.forEach { element ->
            if (element == id) {
                counter ++
            }
        }
        if (counter==2) {
            currentlyOpenedCards.clear()
            _openedCards.value=_openedCards.value?.toMutableList()?.apply {
                add(id)
            }

        }
        if (currentlyOpenedCards.size == 2) {
            Handler().postDelayed({
                currentlyOpenedCards.clear()
                _openedCards.value=_openedCards.value?.toMutableList()?.apply {
                    add(-1)
                }
            }, 1000)

        }
    }
}