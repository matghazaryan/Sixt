package com.mg.android.list.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mg.android.list.R
import com.mg.android.list.utils.ListItemDiffCallback
import com.mg.android.list.viewmodel.ListViewModel
import com.mg.android.model.Car

class ListAdapter(private val viewModel: ListViewModel): RecyclerView.Adapter<ListViewHolder>() {

    private val cars: MutableList<Car> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount(): Int
            = cars.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int)
            = holder.bindTo(cars[position], viewModel)


    fun updateData(items: List<Car>) {
        val diffCallback =
            ListItemDiffCallback(cars, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        cars.clear()
        cars.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}