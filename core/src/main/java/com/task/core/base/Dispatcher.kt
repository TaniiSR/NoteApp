package com.task.core.base

sealed class Dispatcher {
    object Main : Dispatcher()
    object Background : Dispatcher()
    object LongOperation : Dispatcher()
}