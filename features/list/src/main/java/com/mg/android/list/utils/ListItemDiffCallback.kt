package com.mg.android.list.utils

import androidx.recyclerview.widget.DiffUtil
import com.mg.android.model.Car

class ListItemDiffCallback(private val oldList: List<Car>,
                           private val newList: List<Car>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].carImageUrl == newList[newItemPosition].carImageUrl
                && oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}