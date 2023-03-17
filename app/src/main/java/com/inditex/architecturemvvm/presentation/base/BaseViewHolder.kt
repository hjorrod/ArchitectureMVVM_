package com.inditex.architecturemvvm.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}

/*abstract class BaseViewHolder<T>(binding: View) : RecyclerView.ViewHolder(binding.rootView) {
    abstract fun bind(item: T)
}*/