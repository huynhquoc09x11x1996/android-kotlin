package com.example.internhbaoquoc.recycleviewkotlin

import android.view.View

interface recyclerViewItemClick {
    fun onItemLongClick(position: Int, v: View, isLongCLick: Boolean)
}