package com.inditex.architecturemvvm.presentation.util

fun interface OnAddDataSetListener<T> {
    fun addDataSet(items: List<T>)
}

fun interface OnShowErrorListener {
    fun showError(message: String)
}