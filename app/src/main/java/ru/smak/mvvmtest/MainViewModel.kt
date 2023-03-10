package ru.smak.mvvmtest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    var hours = mutableStateOf(12U)

    var minutes = mutableStateOf(0U)

    var angel = mutableStateOf(0.0)

    private val clock = Clock()

    fun calc(){
        angel.value = clock.getAngle(hours.value, minutes.value)
    }
}