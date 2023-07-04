package com.fajar.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajar.myapplication.data.local.Yugi
import com.fajar.myapplication.databinding.CardItemBinding
import com.fajar.myapplication.ui.interfaces.ItemClickCallback
import com.fajar.myapplication.util.Constant
import com.fajar.myapplication.util.MyDiffUtil

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: ItemClickCallback
    var cardList = ArrayList<Yugi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cardList.size

    fun setOnItemClickCallback(onItemClickCallback: ItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<Yugi>) {
        val diffUtils = MyDiffUtil(cardList, items)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        cardList.clear()
        cardList.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Yugi) {
            binding.apply {
                binding.apply {
                    Glide.with(itemView.context)
                        .load("${Constant.IMAGE_BASE_URL}${data.cardImage}")
                        .centerCrop()
                        .into(imgItemPhoto)
                    tvName.text = data.name

                    itemView.setOnClickListener {
                        onItemClickCallback.onItemClicked(data)
                    }
                }

            }
        }
    }
}