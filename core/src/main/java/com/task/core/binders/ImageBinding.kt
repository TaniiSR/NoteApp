package com.task.core.binders

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object ImageBinding {
    @BindingAdapter("bitmapSrc")
    @JvmStatic
    fun setBitmapSrc(view: AppCompatImageView, bitmap: Bitmap?) {
        if (bitmap != null)
            view.setImageBitmap(bitmap)
    }

    @JvmStatic
    @BindingAdapter("app:srcDrawable")
    fun setImageViewResource(imageView: ImageView?, resource: Int) {
        imageView?.setImageResource(resource)
    }

    @JvmStatic
    @BindingAdapter(value = ["loadImg", "errorImg", "placeHolder"], requireAll = false)
    fun loadImageSrc(
        imageView: AppCompatImageView,
        resource: String?,
        errorRecourse: Int? = null,
        placeRecourse: Drawable? = null
    ) {
        resource?.let {
            errorRecourse?.let { err ->
                Glide.with(imageView).load(resource).placeholder(placeRecourse).error(err)
                    .into(imageView)
            } ?: Glide.with(imageView).load(resource).into(imageView)
        } ?: Glide.with(imageView).load(resource)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("recycleViewAdapter")
    fun setRecycleViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?,
    ) {
        if (null == adapter)
            return
        recyclerView.adapter = adapter
    }
}
