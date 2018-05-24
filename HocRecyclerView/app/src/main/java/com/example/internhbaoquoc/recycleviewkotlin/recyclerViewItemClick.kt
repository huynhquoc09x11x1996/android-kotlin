package com.example.internhbaoquoc.recycleviewkotlin

import android.view.View
/*
* interface cho onLongClick tại mỗi item trên recyclerview
* phương thức gồm:
* @param position : vị trí item thứ position
* @param v: đang là view nào, để lấy các component
* @param isLongCLick: long click or not
*
* */
interface recyclerViewItemClick {
    fun onItemLongClick(position: Int, v: View, isLongCLick: Boolean)
}