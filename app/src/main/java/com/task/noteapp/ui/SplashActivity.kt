package com.task.noteapp.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import com.task.core.BR
import com.task.core.base.BaseActivity
import com.task.core.extensions.hideSystemUI
import com.task.core.extensions.launchActivity
import com.task.core.extensions.showSystemUI
import com.task.mynoteapp.ui.mainnote.NoteMainActivity
import com.task.noteapp.R
import com.task.noteapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, ISplash.State, ISplash.ViewModel>(),
    ISplash.View {
    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun getBindingVariable(): Int = BR.viewModel
    override val viewModel: ISplash.ViewModel by viewModels<SplashVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI(mViewBinding.root)
        initCountDownTimer(2)
    }

    private fun initCountDownTimer(time: Int) {
        object : CountDownTimer(time.times(1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startNoteMainActivity()
            }
        }.start()
    }

    private fun startNoteMainActivity() {
        showSystemUI(mViewBinding.root)
        launchActivity<NoteMainActivity>(
            options = Bundle(),
            clearPrevious = true
        ) {

        }
    }
}