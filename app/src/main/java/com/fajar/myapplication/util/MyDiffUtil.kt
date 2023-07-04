package com.fajar.myapplication.util

import androidx.recyclerview.widget.DiffUtil
import com.fajar.myapplication.data.local.Yugi

class MyDiffUtil(
    private val oldList: List<Yugi>,
    private val newList: List<Yugi>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}