package com.inditex.architecturemvvm.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.inditex.architecturemvvm.R
import com.inditex.architecturemvvm.data.util.InternetConnectionObserver
import com.inditex.architecturemvvm.presentation.util.OnShowErrorListener
import com.inditex.architecturemvvm.presentation.util.toast

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layout: Int)
    : AppCompatActivity(), OnShowErrorListener {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        observeInternetConnection()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
    }

    override fun showError(message: String) {
        if(message.isNotEmpty()) message.toast(this)
        else resources.getString(R.string.error_message).toast(this)
    }

    private fun observeInternetConnection() {
        InternetConnectionObserver.get().observe(this) { event ->
            event?.getContentIfNotHandled()?.let {
                showError(it.message)
            }
        }
    }
}