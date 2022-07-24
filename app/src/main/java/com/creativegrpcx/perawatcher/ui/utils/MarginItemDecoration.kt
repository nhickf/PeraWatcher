package com.creativegrpcx.perawatcher.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize: Int , private var orientation : Orientation = Orientation.HORIZONTAL) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {

            if (orientation == Orientation.VERTICAL){
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceSize
                }
                left = spaceSize
                right = spaceSize
                bottom = spaceSize
            }else{
                top = spaceSize
                left = spaceSize
                right = spaceSize
                bottom = spaceSize
            }


        }
    }
}

enum class Orientation {
    HORIZONTAL,
    VERTICAL
}
