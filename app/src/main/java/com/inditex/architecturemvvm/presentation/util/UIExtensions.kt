package com.inditex.architecturemvvm.presentation.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

inline val <reified T> T.TAG: String
    get() = T::class.java.canonicalName ?: T::class.simpleName ?: T::class.java.simpleName

fun ImageView.loadUrl(url: String) = loadUri(Uri.parse(url))

fun ImageView.loadUri(uri: Uri) {
    Picasso.get().load(uri).into(this)
}

fun String.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this, duration).apply { show() }
}
