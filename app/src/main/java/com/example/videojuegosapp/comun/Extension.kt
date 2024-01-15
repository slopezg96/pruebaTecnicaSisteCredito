package com.example.videojuegosapp.comun

import android.graphics.Rect
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class VerticalItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            bottom = margin
            top = margin
        }
    }
}

class HorizontalItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            left = margin
            right = margin
        }
    }
}

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}

fun String.convertirAFormatoDiaMesAnio(): String? {
    val date = try {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        return null
    }
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
}

fun TextView.makeCollapsible(
    maxLinesCollapsed: Int,
    maxLinesExpanded: Int,
    expandMoreDrawable: ImageView
) {
    maxLines = maxLinesCollapsed

    setOnClickListener {
        maxLines = if (maxLines == maxLinesCollapsed) {
            expandMoreDrawable.rotation = 180f
            maxLinesExpanded
        } else {
            expandMoreDrawable.rotation = 0f
            maxLinesCollapsed
        }
        TransitionManager.beginDelayedTransition(parent as ViewGroup)
    }
}