package ru.smak.mvvmtest

import kotlin.math.abs

class Clock {

    fun getAngle(hours: UInt, minutes: UInt): Double{
        val wholeMin = hours * 60U + minutes
        val hrs = (wholeMin / 60U) % 12U
        val min = wholeMin % 60U
        val ma = min.toDouble() * 6
        val ha = hrs.toDouble() * 30 + 0.5 * min.toDouble()
        var delta = abs(ha - ma)
        if (delta > 180) delta  = 360 - delta
        return delta
    }
}