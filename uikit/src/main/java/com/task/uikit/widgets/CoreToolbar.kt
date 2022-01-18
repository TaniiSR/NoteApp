package com.task.uikit.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.widget.TextViewCompat.setTextAppearance
import com.task.core.binders.ImageBinding
import com.task.uikit.R


class CoreToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    Toolbar(context, attrs) {
    var titleText: String? = null
        set(value) {
            field = value
            if (!titleText.isNullOrEmpty()) {
                view.findViewById<TextView>(R.id.toolbarTitle).text = titleText
                invalidate()
            }
        }

    var toolbarTitleStyle: Int? = null
        set(value) {
            field = value
            toolbarTitleStyle?.let {
                setTextAppearance(view.findViewById<TextView>(R.id.toolbarTitle), it);
            }
        }

    var leftIconVisibility: Boolean? = null
        set(value) {
            field = value
            view.findViewById<ImageView>(R.id.ivLeftIcon).visibility =
                if (leftIconVisibility == true) View.VISIBLE else View.INVISIBLE
            invalidate()

        }
    var rightIconVisibility: Boolean? = null
        set(value) {
            field = value
            view.findViewById<ImageView>(R.id.ivRightIcon).visibility =
                if (rightIconVisibility == true) View.VISIBLE else View.INVISIBLE
            invalidate()

        }

    var rightTitleVisibility: Boolean? = null
        set(value) {
            field = value
            view.findViewById<TextView>(R.id.tvRightText).visibility =
                if (rightTitleVisibility == true) View.VISIBLE else View.INVISIBLE
            invalidate()

        }

    var rightIcon: Int? = null
        set(value) {
            field = value
            rightIcon?.let {
                ImageBinding.setImageViewResource(
                    view.findViewById<ImageView>(R.id.ivRightIcon),
                    it
                )
                invalidate()
            }
        }

    var leftIcon: Int? = null
        set(value) {
            field = value
            leftIcon?.let {
                ImageBinding.setImageViewResource(
                    view.findViewById<ImageView>(R.id.ivLeftIcon),
                    it
                )
                invalidate()
            }
        }

    var titleDrawableEnd: Drawable? = null
        set(value) {
            field = value
            titleDrawableEnd?.let {
                it.setBounds(0, 0, 60, 60)
                view.findViewById<TextView>(R.id.toolbarTitle)
                    .setCompoundDrawables(null, null, it, null)
                invalidate()
            }
        }
    var onClick: View.OnClickListener? = null
        set(value) {
            field = value
            view.findViewById<TextView>(R.id.tvRightText).setOnClickListener(onClick)
            view.findViewById<ImageView>(R.id.ivRightIcon).setOnClickListener(onClick)
            view.findViewById<ImageView>(R.id.ivLeftIcon).setOnClickListener(onClick)
            view.findViewById<TextView>(R.id.toolbarTitle).setOnClickListener(onClick)
            invalidate()
        }

    var view: View = LayoutInflater.from(context)
        .inflate(R.layout.layout_core_toolbar, this, true)

    init {
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.CoreToolbar, 0, 0)
            typedArray.recycle()
        }
    }
}