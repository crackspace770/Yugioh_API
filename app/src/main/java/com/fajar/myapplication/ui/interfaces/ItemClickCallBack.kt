package com.fajar.myapplication.ui.interfaces

import com.fajar.myapplication.data.local.Yugi

interface ItemClickCallback {
    fun onItemClicked(cardItem: Yugi)
}