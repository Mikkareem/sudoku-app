package com.techullurgy.sudoku.domain.core

import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.temporal.ChronoUnit

object StopWatch {
    private var coroutineScope: CoroutineScope? = CoroutineScope(Dispatchers.Default)

    private var stopWatchListener: StopWatchListener? = null

    private var savedTime: LocalTime? = null
    private var isTimerRunning = false

    fun addListener(stopWatchListener: StopWatchListener) {
        StopWatch.stopWatchListener = stopWatchListener
    }

    fun pause() {
        isTimerRunning = false
        savedTime = sdkEqualAndUp(Build.VERSION_CODES.O) { LocalTime.now() }
    }

    fun stop() {
        pause()
        coroutineScope?.cancel()
        coroutineScope = null
        stopWatchListener?.onStop()
    }

    fun start() {
        stop()
        coroutineScope = CoroutineScope(Dispatchers.Default)

        isTimerRunning = true

        coroutineScope?.launch {
            while(isTimerRunning) {
                delay(970L)
                val totalElapsedSeconds = sdkEqualAndUp(Build.VERSION_CODES.O) {
                    (savedTime ?: LocalTime.now()).until(LocalTime.now(), ChronoUnit.SECONDS)
                } ?: 0L
                stopWatchListener?.onTick(toTimeString(totalElapsedSeconds))
            }
        }
    }

    fun cancel() = stop().also {
        savedTime = null
        stopWatchListener = null
    }

    private fun toTimeString(totalElapsedSeconds: Long): String = "${(totalElapsedSeconds / 60).toString().padStart(2, '0')}:${(totalElapsedSeconds % 60).toString().padStart(2, '0')}"
}

interface StopWatchListener {
    fun onTick(elapsedTimeValue: String)
    fun onStop()
}

inline fun <T> sdkEqualAndUp(versionCode: Int, crossinline callback: () -> T): T? {
    if(Build.VERSION.SDK_INT >= versionCode) {
        return callback()
    }
    return null
}