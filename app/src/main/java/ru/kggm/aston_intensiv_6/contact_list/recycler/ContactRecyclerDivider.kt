package ru.kggm.aston_intensiv_6.contact_list.recycler

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.kggm.aston_intensiv_6.R
import ru.kggm.aston_intensiv_6.utility.dip2px
import kotlin.math.roundToInt

class ContactRecyclerDivider(private val context: Context)
: DividerItemDecoration(context, RecyclerView.VERTICAL) {
    init {
        ResourcesCompat
            .getDrawable(context.resources, R.drawable.drawable_contact_list_divider, context.theme)
            ?.let { setDrawable(it) }
    }

    private val margin by lazy {
        context.dip2px(
            context.resources.getDimension(R.dimen.dimen_contact_recycler_divider_margin)
        ).roundToInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            top = margin
            bottom = margin
            left = margin
            right = margin
        }
    }
}