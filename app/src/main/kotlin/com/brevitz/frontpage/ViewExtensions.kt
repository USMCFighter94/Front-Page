package com.brevitz.frontpage

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.cena() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}