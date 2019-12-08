package com.mg.android.list.views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mg.android.list.databinding.ItemListBinding
import com.mg.android.list.viewmodel.ListViewModel
import com.mg.android.model.Car

class ListViewHolder(parent: View): RecyclerView.ViewHolder(parent) {

    private val binding = ItemListBinding.bind(parent)

    fun bindTo(car: Car, viewModel: ListViewModel) {
        binding.car = car
        binding.viewmodel = viewModel
    }
}