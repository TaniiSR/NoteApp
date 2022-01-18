package com.task.core.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment


fun makeToast(ctx: Context?, msg: String, duration: Int): Toast? {
    return ctx?.let {
        val toast = Toast.makeText(ctx, msg, duration)
        toast.show()
        // remove from list after 4 seconds (longest toast time is 3.5 seconds)
        toast.view?.postDelayed({
            ToastQueue.removeToast(toast)
        }, 4000L)
        ToastQueue.toastQueue.add(toast)
        toast
    }
}

private object ToastQueue {
    val toastQueue = mutableListOf<Toast>()

    fun cancelToasts() {
        toastQueue.forEach { it.cancel() }
        toastQueue.clear()
    }

    fun removeToast(toast: Toast) = toastQueue.remove(toast)

}

fun Fragment?.toast(msg: String) = makeToast(
    this?.context,
    msg,
    Toast.LENGTH_LONG
)

fun Any?.log(tag: String, msg: String) {
    Log.i(tag, msg)
}
