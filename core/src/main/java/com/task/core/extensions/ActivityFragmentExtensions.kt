package com.task.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.florent37.inlineactivityresult.kotlin.startForResult
import com.task.core.utils.EXTRA


/**
 * Extensions for simpler launching of Activities
 */
fun Fragment.finishAffinity() {
    activity?.finishAffinity()
}

fun Fragment.finish() {
    activity?.finish()
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

/**
 * Extension method to get a new Intent for an Activity class
 */
inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

/**
 * Create an intent for [T] and apply a lambda on it
 */
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    clearPrevious: Boolean = false,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.putExtra(EXTRA, options)
    if (clearPrevious) finish()
    startActivityForResult(intent, requestCode, options)

}

inline fun <reified T : Any> FragmentActivity.launchActivityForResult(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {},
    noinline completionHandler: ((resultCode: Int, data: Intent?) -> Unit)? = null
) {
    completionHandler?.let {
        val intent = newIntent<T>(this)
        intent.init()
        intent.putExtra(EXTRA, options)
        this@launchActivityForResult.startForResult(intent) { result ->
            it.invoke(result.resultCode, result.data)
        }.onFailed { result ->
            it.invoke(result.resultCode, result.data)
        }
    } ?: run {
        launchActivity<T>(
            requestCode = requestCode,
            options = options,
            init = init
        )
    }
}

inline fun <reified T : Any> Fragment.launchActivity(
    options: Bundle? = null,
    clearPrevious: Boolean = false,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(requireContext())
    intent.init()
    intent.putExtra(EXTRA, options)
    startActivity(intent, options)
    if (clearPrevious) finish()

}

fun Activity.hideSystemUI(rootView: View) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, rootView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Activity.showSystemUI(rootView: View) {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        rootView
    ).show(WindowInsetsCompat.Type.systemBars())
}

